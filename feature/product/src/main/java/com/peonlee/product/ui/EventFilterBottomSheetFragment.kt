package com.peonlee.product.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import com.google.android.flexbox.FlexboxLayout
import com.peonlee.core.ui.base.BaseBottomSheetFragment
import com.peonlee.core.ui.designsystem.selector.SmallSelector
import com.peonlee.model.product.ProductSearchConditionUiModel
import com.peonlee.model.type.EventType
import com.peonlee.model.type.StoreType
import com.peonlee.product.databinding.ItemFilterChipBinding
import com.peonlee.product.databinding.ItemSelectorFilterBinding
import com.peonlee.product.databinding.LayoutSelectorFilterBinding

class EventFilterBottomSheetFragment(
    private val onEventSelect: (List<StoreType>, List<EventType>) -> Unit
) : BaseBottomSheetFragment("행사") {

    private var selectedStore = mutableListOf<StoreType>()
    private var selectedEvent = mutableListOf<EventType>()
    private var eventLayout: FlexboxLayout? = null

    override fun getFilterLayout(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): View {
        val listLayout = LayoutSelectorFilterBinding.inflate(layoutInflater, parent, false).root

        // 편의점
        listLayout.addView(
            ItemSelectorFilterBinding.inflate(layoutInflater).apply {
                tvTitle.text = "편의점별 행사"
                StoreType.values().forEach { store ->
                    flexEventChip.addView(
                        ItemFilterChipBinding.inflate(layoutInflater).apply {
                            if (store in selectedStore) root.setFillColor() else root.setCancelColor()
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
                eventLayout = flexEventChip
                EventType.values().forEach { event ->
                    flexEventChip.addView(
                        ItemFilterChipBinding.inflate(layoutInflater).apply {
                            if (event in selectedEvent) root.setFillColor() else root.setCancelColor()
                            root.text = event.eventName
                            root.setOnClickListener { onClickEvent(root, event) }
                        }.root
                    )
                }
            }.root
        )
        return listLayout
    }

    override fun onClickComplete() {
        onEventSelect(selectedStore, selectedEvent)
    }

    override fun setChangedFilter(productSearchCondition: ProductSearchConditionUiModel): BaseBottomSheetFragment {
        selectedStore = productSearchCondition.stores?.toMutableList() ?: mutableListOf()
        selectedEvent = productSearchCondition.events?.toMutableList() ?: mutableListOf()
        return this
    }

    private fun onClickStoreType(
        selector: SmallSelector,
        storeType: StoreType
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
        selector: SmallSelector,
        eventType: EventType
    ) {
        if (eventType in selectedEvent) {
            // 이미 선택된 행사인 경우
            selectedEvent.remove(eventType)
            selector.setCancelColor()
        } else {
            // 처음 선택하는 행사인 경우
            when (eventType) {
                EventType.ALL -> {
                    eventLayout?.children?.forEach {
                        (it as? SmallSelector)?.setCancelColor()
                    }
                    selector.setFillColor()
                    selectedEvent.clear()
                    selectedEvent.add(eventType)
                }

                else -> {
                    if (EventType.ALL in selectedEvent) {
                        eventLayout?.children?.forEach {
                            (it as? SmallSelector)?.setCancelColor()
                        }
                        selectedEvent.clear()
                    }
                    selectedEvent.add(eventType)
                    selector.setFillColor()
                }
            }
        }
    }
}
