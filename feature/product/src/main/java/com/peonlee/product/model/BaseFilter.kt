package com.peonlee.product.model

import androidx.annotation.DrawableRes
import com.peonlee.core.ui.R

enum class FilterType { INIT, PRICE, EVENT, CATEGORY }
sealed interface BaseFilter {
    val title: String
    val isSelected: Boolean
    val filterType: FilterType
    val iconId: Int

    // 초기화
    object Init : BaseFilter {
        override val title: String = "초기화"
        override val isSelected: Boolean = false
        override val filterType: FilterType = FilterType.INIT

        @DrawableRes
        override val iconId: Int = R.drawable.ic_reload
    }

    // 가격
    data class Price(
        override val title: String = "가격",
        override val isSelected: Boolean = false,
        override val filterType: FilterType = FilterType.PRICE,
        @DrawableRes override val iconId: Int = R.drawable.ic_chevron_bottom
    ) : BaseFilter

    // 행사
    data class Event(
        override val title: String = "행사",
        override val isSelected: Boolean = false,
        override val filterType: FilterType = FilterType.EVENT,
        @DrawableRes override val iconId: Int = R.drawable.ic_chevron_bottom
    ) : BaseFilter

    // 카테고리
    data class Category(
        override val title: String = "카테고리",
        override val isSelected: Boolean = false,
        override val filterType: FilterType = FilterType.EVENT,
        @DrawableRes override val iconId: Int = R.drawable.ic_chevron_bottom
    ) : BaseFilter
}

val baseFilterSet = listOf(
    BaseFilter.Price(), BaseFilter.Event(), BaseFilter.Category()
)
