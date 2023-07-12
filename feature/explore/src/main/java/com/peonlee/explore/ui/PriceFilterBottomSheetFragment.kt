package com.peonlee.explore.ui

import android.view.View
import android.view.ViewGroup
import com.peonlee.core.ui.base.BaseBottomSheetFragment
import com.peonlee.core.ui.extensions.toFormattedMoney
import com.peonlee.explore.R
import com.peonlee.explore.databinding.ItemPriceFilterBinding
import com.peonlee.explore.databinding.LayoutPriceFilterBinding

class PriceFilterBottomSheetFragment : BaseBottomSheetFragment("가격") {

    private var currentSelectedPrice: PriceFilter? = null

    override fun getFilterLayout(parent: ViewGroup): View {
        val radioGroup = LayoutPriceFilterBinding.inflate(layoutInflater, parent, false).root

        // 하위 Filter 추가
        PriceFilter.values().forEach {
            val priceFilterItem = ItemPriceFilterBinding.inflate(layoutInflater).root
            priceFilterItem.text = getRangePrice(it)
            radioGroup.addView(priceFilterItem)
        }

        radioGroup.setOnCheckedChangeListener { _, position ->
            currentSelectedPrice = PriceFilter.values().getOrNull(position - 1)
        }
        return radioGroup
    }

    private fun getRangePrice(priceFilter: PriceFilter): String {
        return if (priceFilter.maxPrice != null) {
            getString(
                R.string.full_price_range,
                priceFilter.minPrice.toFormattedMoney(),
                priceFilter.maxPrice.toFormattedMoney()
            )
        } else {
            getString(
                R.string.half_price_range,
                priceFilter.minPrice.toFormattedMoney()
            )
        }
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
