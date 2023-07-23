package com.peonlee.onboading

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardViewModel @Inject constructor() : ViewModel() {
    var evaluatedProductCount: Int = 0
        private set

    fun addEvaluatedProductCount() {
        evaluatedProductCount++
    }
}
