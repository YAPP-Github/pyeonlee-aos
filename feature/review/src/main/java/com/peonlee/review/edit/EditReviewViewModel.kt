package com.peonlee.review.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peonlee.data.Result
import com.peonlee.data.review.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditReviewViewModel @Inject constructor(
    private val reviewRepository: ReviewRepository
) : ViewModel() {
    private val _review = MutableStateFlow("")
    val review: StateFlow<String> = _review.asStateFlow()

    fun setReview(newReview: String?) {
        if (newReview == null) return
        _review.value = newReview
    }

    /**
     * 사용자가 작성한 리뷰 저장
     */
    fun saveReview() {
        // 1. 리뷰 작성 요청
        val editedReview = _review.value
        // TODO 글자 수가 부족할 때 UI로 알려 주기
        if (editedReview.length < 10) return

        viewModelScope.launch {
            val saveReviewResult = reviewRepository.saveReview(
                productId = 6400,
                review = editedReview
            )
            when (saveReviewResult) {
                is Result.Error -> { /* TODO Toast */
                    println(saveReviewResult.exception)
                }

                is Result.Success -> { /* TODO Toast + BackStack */
                    println("Success")
                }
            }
        }
    }
}
