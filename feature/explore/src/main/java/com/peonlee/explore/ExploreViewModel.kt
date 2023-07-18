package com.peonlee.explore

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor() : ViewModel() {
    private val _keyword: MutableStateFlow<String> = MutableStateFlow("")
    val keyword = _keyword.asStateFlow()

    fun setKeyword(newKeyword: String) {
        _keyword.value = newKeyword
    }
}
