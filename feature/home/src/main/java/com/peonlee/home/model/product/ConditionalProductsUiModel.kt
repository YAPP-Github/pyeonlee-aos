package com.peonlee.home.model.product

import com.peonlee.model.MainHomeListItem
import com.peonlee.model.MainHomeViewType
import com.peonlee.model.product.ProductUiModel
import com.peonlee.model.type.SortType

/**
 * 조건별 상품 리스트 Ui Model
 */
data class ConditionalProductsUiModel(
    override val id: Long,
    override val viewType: Enum<MainHomeViewType>,
    val sortType: SortType, // 정렬 type
    val products: List<ProductUiModel> // 해당 조건에 맞는 상품 리스트
) : MainHomeListItem
