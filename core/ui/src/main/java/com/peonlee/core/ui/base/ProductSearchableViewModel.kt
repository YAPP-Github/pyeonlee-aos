package com.peonlee.core.ui.base

import androidx.lifecycle.ViewModel
import com.peonlee.model.product.ProductSearchConditionUiModel
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
}
