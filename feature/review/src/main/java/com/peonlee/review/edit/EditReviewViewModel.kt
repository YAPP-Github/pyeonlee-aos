package com.peonlee.review.edit

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class EditReviewViewModel : ViewModel() {

    private val _review = MutableStateFlow("")
    val review: StateFlow<String> = _review.asStateFlow()

    fun setReview(newReview: String?) {
        if (newReview == null || newReview.length > REVIEW_MAX_LENGTH) return
        _review.value = newReview
    }

    fun saveReview() {
        // TODO 리뷰 저장 로직 추가 예정
    }

    companion object {
        const val REVIEW_MAX_LENGTH = 300
    }
}
