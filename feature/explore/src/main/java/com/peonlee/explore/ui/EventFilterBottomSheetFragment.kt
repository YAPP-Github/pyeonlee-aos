package com.peonlee.explore.ui

import android.view.View
import android.view.ViewGroup
import com.peonlee.core.ui.base.BaseBottomSheetFragment
import com.peonlee.explore.databinding.ItemFilterChipBinding
import com.peonlee.explore.databinding.ItemSelectorFilterBinding
import com.peonlee.explore.databinding.LayoutSelectorFilterBinding
import com.peonlee.model.type.EventType
import com.peonlee.model.type.StoreType

class EventFilterBottomSheetFragment : BaseBottomSheetFragment("행사") {
    override fun getFilterLayout(parent: ViewGroup): View {
        val listLayout = LayoutSelectorFilterBinding.inflate(layoutInflater, parent, false).root

        // 편의점
        listLayout.addView(
            ItemSelectorFilterBinding.inflate(layoutInflater).apply {
                tvTitle.text = "편의점별 행사"
                StoreType.values().forEach { store ->
                    flexEventChip.addView(
                        ItemFilterChipBinding.inflate(layoutInflater).apply {
                            root.text = store.storeName
                        }.root
                    )
                }
            }.root
        )
        // 이벤트(행사)
        listLayout.addView(
            ItemSelectorFilterBinding.inflate(layoutInflater).apply {
                tvTitle.text = "행사 상품"
                EventType.values().forEach { event ->
                    flexEventChip.addView(
                        ItemFilterChipBinding.inflate(layoutInflater).apply {
                            root.text = event.eventName
                        }.root
                    )
                }
            }.root
        )
        return listLayout
    }
}
