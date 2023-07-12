package com.peonlee.explore.ui

import android.view.View
import android.view.ViewGroup
import com.peonlee.core.ui.base.BaseBottomSheetFragment
import com.peonlee.core.ui.designsystem.selector.SmallSelector
import com.peonlee.explore.databinding.ItemFilterChipBinding
import com.peonlee.explore.databinding.ItemSelectorFilterBinding
import com.peonlee.explore.databinding.LayoutSelectorFilterBinding
import com.peonlee.model.type.EventType
import com.peonlee.model.type.StoreType

class EventFilterBottomSheetFragment : BaseBottomSheetFragment("행사") {

    private val selectedStore = mutableListOf<StoreType>()
    private val selectedEvent = mutableListOf<EventType>()

    override fun getFilterLayout(parent: ViewGroup): View {
        val listLayout = LayoutSelectorFilterBinding.inflate(layoutInflater, parent, false).root

        // 편의점
        listLayout.addView(
            ItemSelectorFilterBinding.inflate(layoutInflater).apply {
                tvTitle.text = "편의점별 행사"
                StoreType.values().forEach { store ->
                    flexEventChip.addView(
                        ItemFilterChipBinding.inflate(layoutInflater).apply {
                            root.setCancelColor()
                            root.text = store.storeName
                            root.setOnClickListener { onClickStoreType(root, store) }
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
                            root.setCancelColor()
                            root.text = event.eventName
                            root.setOnClickListener { onClickEvent(root, event) }
                        }.root
                    )
                }
            }.root
        )
        return listLayout
    }

    private fun onClickStoreType(
        selector: SmallSelector, storeType: StoreType
    ) {
        if (storeType in selectedStore) {
            selectedStore.remove(storeType)
            selector.setCancelColor()
        } else {
            selectedStore.add(storeType)
            selector.setFillColor()
        }
    }

    private fun onClickEvent(
        selector: SmallSelector, eventType: EventType
    ) {
        if (eventType in selectedEvent) {
            selectedEvent.remove(eventType)
            selector.setCancelColor()
        } else {
            selectedEvent.add(eventType)
            selector.setFillColor()
        }
    }
}
