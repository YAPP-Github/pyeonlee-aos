package com.peonlee.home.model.product

import com.peonlee.model.MainHomeListItem
import com.peonlee.model.MainHomeViewType
import com.peonlee.model.product.PRODUCTS_TEST_DOUBLE
import com.peonlee.model.type.StoreType

/**
 * 편의점 별 이벤트 상품들
 */
data class EventByStoresUiModel(
    override val id: Long,
    override val viewType: MainHomeViewType = MainHomeViewType.EVENT_BY_STORE,
    val stores: List<ProductsByStoreUiModel>
) : MainHomeListItem

val EVENT_PRODUCTS_DUMMY = EventByStoresUiModel(
    id = -30,
    stores = StoreType.values().map {
        ProductsByStoreUiModel(
            stores = it,
            products = PRODUCTS_TEST_DOUBLE.subList(0, 6)
        )
    }
)
