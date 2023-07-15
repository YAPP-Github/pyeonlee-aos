package com.peonlee.model.product

import com.peonlee.model.type.Category
import com.peonlee.model.type.EventType
import com.peonlee.model.type.PriceFilter
import com.peonlee.model.type.SortType
import com.peonlee.model.type.StoreType

data class ProductSearchConditionUiModel(
    val keyword: String = "", // 키워드
    val sortedBy: SortType = SortType.RECENT, // 정렬 기준
    val price: PriceFilter? = null, // 가격 조건
    val stores: List<StoreType>? = null, // 상점 조건
    val events: List<EventType>? = null, // 행사 조건
    val categories: List<Category>? = null // 카테고리 조건
)
