package com.peonlee.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _currentTab = MutableStateFlow(R.id.navHome)
    val currentTab: StateFlow<Int> = _currentTab.asStateFlow()

    fun changeTab(tabId: Int) {
        _currentTab.value = tabId
    }
}
