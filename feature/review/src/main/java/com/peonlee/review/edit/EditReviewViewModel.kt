package com.peonlee.review.edit

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EditReviewViewModel @Inject constructor() : ViewModel() {
    private val _review = MutableStateFlow("")
    val review: StateFlow<String> = _review.asStateFlow()

    fun setReview(newReview: String?) {
        if (newReview == null) return
        _review.value = newReview
    }

    fun saveReview() {
        // TODO 리뷰 저장 로직 추가 예정
    }
}
