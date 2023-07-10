package com.peonlee.explore

import androidx.lifecycle.ViewModel
import com.peonlee.model.type.SortType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor() : ViewModel() {
    // 현재 선택 중인 정렬 타입
    private val _productSortType = MutableStateFlow(SortType.LATEST)

    init {
        // TODO 정렬 타입이 변경될 때마다 서버 에서 상품 정보(페이징) 요청
    }

    /**
     * 정렬 타입 변경
     */
    fun setProductSortType(sortType: SortType) {
        // 동일한 tab 을 클릭한 경우 에는 무시
        if (_productSortType.value == sortType) return
        _productSortType.value = sortType
    }
}
