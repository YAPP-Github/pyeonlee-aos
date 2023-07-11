package com.peonlee.explore.ui

import android.view.View
import com.peonlee.core.ui.base.BaseBottomSheetFragment
import com.peonlee.explore.databinding.ItemPriceFilterBinding
import com.peonlee.explore.databinding.LayoutPriceFilterBinding

class PriceFilterBottomSheetFragment : BaseBottomSheetFragment("가격") {
    override fun getFilterLayout(): View {
        val radioGroup = LayoutPriceFilterBinding.inflate(layoutInflater).root

        // 하위 Filter 추가
        PriceFilter.values().forEach {
            val priceFilterItem = ItemPriceFilterBinding.inflate(layoutInflater).root
            priceFilterItem.text = it.minPrice.toString()
            radioGroup.addView(priceFilterItem)
        }
        return radioGroup
    }

    private enum class PriceFilter(
        val minPrice: Int,
        val maxPrice: Int?
    ) {
        ONE_POINT_FIVE(0, 1_500),
        FIVE_ZERO(1_500, 5_000),
        TEN_ZERO(5_000, 10_000),
        OVER_TEN(10_000, null)
    }
}
