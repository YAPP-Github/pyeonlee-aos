package com.peonlee.explore.ui

import android.view.View
import com.peonlee.core.ui.base.BaseBottomSheetFragment
import com.peonlee.explore.databinding.LayoutPriceFilterBinding

class PriceFilterBottomSheetFragment : BaseBottomSheetFragment("가격") {
    override fun getFilterLayout(): View {
        val radioGroup = LayoutPriceFilterBinding.inflate(layoutInflater)
        return radioGroup.root
    }
}
