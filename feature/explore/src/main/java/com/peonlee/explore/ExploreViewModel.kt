package com.peonlee.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peonlee.model.product.ProductSearchConditionUiModel
import com.peonlee.model.type.Category
import com.peonlee.model.type.EventType
import com.peonlee.model.type.PriceFilter
import com.peonlee.model.type.SortType
import com.peonlee.model.type.StoreType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor() : ViewModel() {
    // 현재 상품 검색 조건
    private val _productSearchCondition = MutableStateFlow(ProductSearchConditionUiModel())
    val productSearchCondition: StateFlow<ProductSearchConditionUiModel> = _productSearchCondition.asStateFlow()

    init {
        viewModelScope.launch {
            _productSearchCondition.collect {
                // TODO 검색 API 요청
            }
        }
    }

    // 정렬 타입 변경
    fun setProductSortType(sortType: SortType) {
        _productSearchCondition.value = _productSearchCondition.value.copy(sortedBy = sortType)
    }

    // 가격 필터 선택
    fun setPriceFilter(priceFilter: PriceFilter?) {
        _productSearchCondition.value = _productSearchCondition.value.copy(price = priceFilter)
    }

    // 이벤트 선택
    fun setEventFilter(stores: List<StoreType>, events: List<EventType>) {
        _productSearchCondition.value = _productSearchCondition.value.copy(
            stores = stores,
            events = events
        )
    }

    // 카테고리 선택
    fun setCategoryFilter(categories: List<Category>) {
        _productSearchCondition.value = _productSearchCondition.value.copy(
            categories = categories
        )
    }
}
