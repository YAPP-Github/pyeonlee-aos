package com.peonlee.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.peonlee.core.ui.base.ProductSearchableViewModel
import com.peonlee.model.product.ProductSearchConditionUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor() : ProductSearchableViewModel() {
    fun setKeyword(newKeyword: String) {
        super.setProductSearchCondition(
            _productSearchCondition.value.copy(keyword = newKeyword)
        )
    }

    class ExploreViewModelFactory() : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ExploreViewModel::class.java)) {
                return ExploreViewModel() as T
            }
            throw IllegalArgumentException("unKnown ViewModel class")
        }
    }
}
