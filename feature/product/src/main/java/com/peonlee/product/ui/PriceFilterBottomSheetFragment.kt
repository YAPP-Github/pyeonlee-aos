package com.peonlee.product.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peonlee.core.ui.base.BaseBottomSheetFragment
import com.peonlee.model.product.ProductSearchConditionUiModel
import com.peonlee.model.type.PriceFilter
import com.peonlee.model.type.toRangeString
import com.peonlee.product.databinding.ItemPriceFilterBinding
import com.peonlee.product.databinding.LayoutPriceFilterBinding

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

        currentSelectedPrice?.let {
            radioGroup.check(radioGroup.getChildAt(PriceFilter.values().indexOf(currentSelectedPrice)).id)
        }
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val position = group.indexOfChild(group.findViewById(checkedId))
            currentSelectedPrice = PriceFilter.values().getOrNull(position)
        }
        return radioGroup
    }

    override fun setChangedFilter(productSearchCondition: ProductSearchConditionUiModel): BaseBottomSheetFragment {
        currentSelectedPrice = productSearchCondition.price
        return this
    }

    override fun onClickComplete() {
        onPriceSelect(currentSelectedPrice)
    }
}
