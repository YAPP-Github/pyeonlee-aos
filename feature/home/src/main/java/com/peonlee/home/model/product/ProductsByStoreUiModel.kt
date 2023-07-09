package com.peonlee.home.model.product

import com.peonlee.model.product.ProductUiModel
import com.peonlee.model.type.StoreType

/**
 * 특정 편의점 의 이벤트 상품
 */
data class ProductsByStoreUiModel(
    val stores: StoreType,
    val products: List<ProductUiModel>
)
