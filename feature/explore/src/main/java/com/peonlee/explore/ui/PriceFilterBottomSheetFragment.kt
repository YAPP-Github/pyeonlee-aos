package com.peonlee.explore.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peonlee.core.ui.base.BaseBottomSheetFragment
import com.peonlee.explore.databinding.ItemPriceFilterBinding
import com.peonlee.explore.databinding.LayoutPriceFilterBinding
import com.peonlee.model.type.PriceFilter
import com.peonlee.model.type.toRangeString

class PriceFilterBottomSheetFragment(
    private val onPriceSelect: (PriceFilter?) -> Unit
) : BaseBottomSheetFragment("가격") {

    private var currentSelectedPrice: PriceFilter? = null

    override fun getFilterLayout(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): View {
        val radioGroup = LayoutPriceFilterBinding.inflate(layoutInflater, parent, false).root

        // 하위 Filter 추가
        PriceFilter.values().forEach {
            val priceFilterItem = ItemPriceFilterBinding.inflate(layoutInflater).root
            priceFilterItem.text = it.toRangeString(priceFilterItem.context)
            radioGroup.addView(priceFilterItem)
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val position  = group.indexOfChild(group.findViewById(checkedId))
            currentSelectedPrice = PriceFilter.values().getOrNull(position)
        }
        return radioGroup
    }

    override fun onClickComplete() {
        onPriceSelect(currentSelectedPrice)
    }
}
