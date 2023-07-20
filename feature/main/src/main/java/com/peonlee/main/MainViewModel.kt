package com.peonlee.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
<<<<<<< HEAD
    private val _selectedNav = MutableStateFlow<Int>(0)
    val selectedNav: StateFlow<Int> = _selectedNav.asStateFlow()

    /**
     * Bottom Navigation 변경
     */
    fun changeSelectedNav(navId: Int) {
        _selectedNav.value = navId
=======
    private val _currentTab = MutableStateFlow(R.id.navHome)
    val currentTab: StateFlow<Int> = _currentTab.asStateFlow()

    fun changeTab(tabId: Int) {
        _currentTab.value = tabId
>>>>>>> af4bc32f1ba80a5b187d7895c7834202e4cf92a8
    }
}
