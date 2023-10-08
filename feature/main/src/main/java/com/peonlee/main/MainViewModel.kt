package com.peonlee.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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

//    /**
//     * 정렬 키워드 변경
//     */
//    override fun changeSortType(sortType: SortType) {
//        _productSearchCondition.value = ProductSearchConditionUiModel(sortedBy = sortType)
//        _selectedNav.value = R.id.navExplore
//    }
//
//    /**
//     * 편의점의 행사 상품 변경
//     */
//    override fun changeStoreType(storeType: StoreType) {
//        _productSearchCondition.value = ProductSearchConditionUiModel(stores = listOf(storeType))
//        _selectedNav.value = R.id.navExplore
//    }

    class MainViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel() as T
            }
            throw IllegalArgumentException("unKnown ViewModel class")
        }
    }
}
