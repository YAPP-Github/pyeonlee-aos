package com.peonlee.home.model.product

import com.peonlee.model.MainHomeListItem
import com.peonlee.model.MainHomeViewType

/**
 * 편의점 별 이벤트 상품들
 */
data class EventByStoresUiModel(
    override val id: Long,
    override val viewType: MainHomeViewType = MainHomeViewType.EVENT_BY_STORE,
    val stores: List<ProductsByStoreUiModel>
) : MainHomeListItem
