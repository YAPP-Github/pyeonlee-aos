package com.peonlee.evaluate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.peonlee.data.Result
import com.peonlee.data.evaluate.EvaluateRepository
import com.peonlee.data.model.Content
import com.peonlee.data.model.Score
import com.peonlee.domain.evaluate.EvaluateProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EvaluateViewModel @Inject constructor(
    evaluateRepository: EvaluateRepository,
    private val evaluateProductUseCase: EvaluateProductUseCase
) : ViewModel() {

    var evaluateCount: Int = 0
        private set

    var likeType = ""
        private set

    lateinit var lastEvaluateItem: Content
        private set

    private val changeProductFlow: MutableStateFlow<List<Content>> = MutableStateFlow(listOf())

    val productFlow: Flow<PagingData<Content>> = evaluateRepository.getSearchProduct()
        .cachedIn(viewModelScope)
        .combine(changeProductFlow) { pagingData, uiState ->
            uiState.fold(pagingData) { currentPagingData, evaluateProduct ->
                evaluateProductApply(currentPagingData, evaluateProduct)
            }
        }

    private val _evaluateState = MutableSharedFlow<EvaluateProductUiState>()
    val evaluateState: SharedFlow<EvaluateProductUiState> get() = _evaluateState.asSharedFlow()

    private fun evaluateProductApply(paging: PagingData<Content>, value: Content): PagingData<Content> {
        return paging.filter {
            value.productId != it.productId
        }
    }

    fun likeProduct(productId: Int) {
        viewModelScope.launch {
            val likeResult = evaluateProductUseCase.likeProduct(productId)
            handleState(likeResult)
        }
    }

    fun dislikeProduct(productId: Int) {
        viewModelScope.launch {
            val dislikeResult = evaluateProductUseCase.dislikeProduct(productId)
            handleState(dislikeResult)
        }
    }

    fun undoProduct(productId: Int) {
        viewModelScope.launch {
            val undoResult = evaluateProductUseCase.undoProduct(productId)
            handleState(undoResult)
        }
    }

    private fun handleState(result: Result<Score>) {
        viewModelScope.launch {
            when (result) {
                is Result.Success -> {
                    when (likeType) {
                        "LIKE" -> {
                            evaluateCount++
                            _evaluateState.emit(EvaluateProductUiState.Like)
                        }
                        "DISLIKE" -> {
                            evaluateCount++
                            _evaluateState.emit(EvaluateProductUiState.Dislike)
                        }
                        "UNDO" -> {
                            evaluateCount--
                            _evaluateState.emit(EvaluateProductUiState.Undo)
                        }
                    }
                }
                is Result.Error -> _evaluateState.emit(EvaluateProductUiState.EvaluateFail(result.exception))
            }
        }
    }

    fun setLastEvaluateItem(evaluateProduct: Content) {
        lastEvaluateItem = evaluateProduct
    }

    fun evaluateProductItem(evaluateProduct: Content) {
        changeProductFlow.value += evaluateProduct
    }

    fun undoProductItem(evaluateProduct: Content) {
        changeProductFlow.value -= evaluateProduct
    }

    fun setLikeType(type: String) {
        likeType = type
    }
}

sealed class EvaluateProductUiState {
    object Like : EvaluateProductUiState()
    object Dislike : EvaluateProductUiState()
    object Undo : EvaluateProductUiState()
    data class EvaluateFail(val exception: Throwable) : EvaluateProductUiState()
}
