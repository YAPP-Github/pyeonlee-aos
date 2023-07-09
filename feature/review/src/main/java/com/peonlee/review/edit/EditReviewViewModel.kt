package com.peonlee.review.edit

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peonlee.data.Result
import com.peonlee.data.review.ReviewRepository
import com.peonlee.review.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditReviewViewModel @Inject constructor(
    private val reviewRepository: ReviewRepository
) : ViewModel() {
    private val _review = MutableStateFlow("")
    val review: StateFlow<String> = _review.asStateFlow()

    private val _editReviewUiEvent = MutableSharedFlow<EditReviewUiEvent>()
    val editReviewUiEvent: SharedFlow<EditReviewUiEvent> = _editReviewUiEvent.asSharedFlow()

    fun setReview(newReview: String?) {
        if (newReview == null) return
        _review.value = newReview
    }

    /**
     * 사용자가 작성한 리뷰 저장
     */
    fun saveReview() {
        viewModelScope.launch {
            // 1. 리뷰 작성 요청
            val editedReview = _review.value
            // TODO 글자 수가 부족할 때 UI로 알려 주기
            if (editedReview.length < 10) {
                _editReviewUiEvent.emit(
                    EditReviewUiEvent.Fail.Message(R.string.fail_to_review_length)
                )
                return@launch
            }
            _editReviewUiEvent.emit(EditReviewUiEvent.Loading)
            val saveReviewResult = reviewRepository.saveReview(
                productId = 6400,
                review = editedReview
            )
            when (saveReviewResult) {
                is Result.Error -> { /* TODO Toast */
                    val exception = saveReviewResult.exception.message
                    _editReviewUiEvent.emit(
                        if (exception != null) {
                            EditReviewUiEvent.Fail.Exception(exception)
                        } else {
                            EditReviewUiEvent.Fail.Message(R.string.fail_to_review_api)
                        }
                    )
                }

                is Result.Success -> { /* TODO Toast + BackStack */
                    _editReviewUiEvent.emit(EditReviewUiEvent.Success)
                }
            }
        }
    }
}

sealed interface EditReviewUiEvent {
    object Loading : EditReviewUiEvent

    // 리뷰 작성 실패 Event
    sealed interface Fail : EditReviewUiEvent {
        data class Exception(
            // Exception 을 통해 전달된 메세지
            val message: String
        ) : Fail

        data class Message(
            // Toast로 보여줄 message resource id
            @StringRes val message: Int
        ) : Fail
    }

    // 리뷰 작성 성공 Event
    object Success : EditReviewUiEvent
}
