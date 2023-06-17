package com.peonlee.home.model.product

import com.peonlee.model.MainHomeListItem
import com.peonlee.model.MainHomeViewType
import com.peonlee.model.product.PRODUCTS_TEST_DOUBLE
import com.peonlee.model.product.ProductUiModel
import com.peonlee.model.type.ConditionType

/**
 * 조건별 상품 리스트 Ui Model
 */
data class ConditionalProductsUiModel(
    override val id: Long,
    override val viewType: Enum<MainHomeViewType>,
    val condition: ConditionType, // 조건 type
    val products: List<ProductUiModel> // 해당 조건에 맞는 상품 리스트
) : MainHomeListItem

val NEW_PRODUCTS = ConditionalProductsUiModel(
    id = -20,
    viewType = MainHomeViewType.CONDITIONAL_PRODUCTS,
    condition = ConditionType.NEW,
    products = PRODUCTS_TEST_DOUBLE
)
val POP_PRODUCTS = ConditionalProductsUiModel(
    id = -24,
    viewType = MainHomeViewType.CONDITIONAL_PRODUCTS,
    condition = ConditionType.POPULAR,
    products = PRODUCTS_TEST_DOUBLE
)
