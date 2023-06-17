package com.peonlee.home.model.product

import com.peonlee.model.MainHomeListItem
import com.peonlee.model.MainHomeViewType
import com.peonlee.model.product.PRODUCTS_TEST_DOUBLE
import com.peonlee.model.product.ProductUiModel
import com.peonlee.model.type.ConditionType

data class ConditionalProductsUiModel(
    override val id: Long,
    override val viewType: Enum<MainHomeViewType>,
    val condition: ConditionType, // 조건 type
    val products: List<ProductUiModel>
) : MainHomeListItem

val NEW_PRODUCTS = ConditionalProductsUiModel(
    id = -20,
    viewType = MainHomeViewType.CONDITIONAL_PRODUCTS,
    condition = ConditionType.NEW,
    products = PRODUCTS_TEST_DOUBLE
)
