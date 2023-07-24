package com.peonlee.core.ui.base

import androidx.lifecycle.ViewModel
import com.peonlee.model.product.ProductSearchConditionUiModel
import com.peonlee.model.type.SortType
import com.peonlee.model.type.StoreType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * 상품을 검색할 수 있는 viewModel
 */
open class ProductSearchableViewModel : ViewModel() {
    protected val _productSearchCondition = MutableStateFlow(ProductSearchConditionUiModel())
    val productSearchCondition: StateFlow<ProductSearchConditionUiModel> = _productSearchCondition.asStateFlow()

    protected fun setProductSearchCondition(
        newProductSearchCondition: ProductSearchConditionUiModel
    ) {
        _productSearchCondition.value = newProductSearchCondition
    }

    /**
     * 정렬 키워드 변경
     */
    open fun changeSortType(sortType: SortType) {}

    /**
     * 편의점의 행사 상품 변경
     */
    open fun changeStoreType(storeType: StoreType) {}
}
