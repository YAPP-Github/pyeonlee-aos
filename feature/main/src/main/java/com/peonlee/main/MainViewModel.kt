package com.peonlee.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _selectedNav = MutableStateFlow<Int>(R.id.navHome)
    val selectedNav: StateFlow<Int> = _selectedNav.asStateFlow()

    /**
     * Bottom Navigation 변경
     */
    fun changeSelectedNav(navId: Int) {
        _selectedNav.value = navId
    }
}
