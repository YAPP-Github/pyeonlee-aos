package com.peonlee.explore.ui

import android.view.View
import com.peonlee.core.ui.base.BaseBottomSheetFragment
import com.peonlee.explore.databinding.LayoutEventFilterBinding
import com.peonlee.model.type.EventType
import com.peonlee.model.type.StoreType

class EventFilterBottomSheetFragment : BaseBottomSheetFragment("행사") {
    override fun getFilterLayout(): View {
        val listLayout = LayoutEventFilterBinding.inflate(layoutInflater).root
        return listLayout
    }

    private sealed interface Filter<T> {
        val title: String
        val filters: List<T>

        data class StoreFilter(
            override val title: String = "편의점별 행사",
            override val filters: List<StoreType> = StoreType.values().toList()
        ) : Filter<StoreType>

        data class EventFilter(
            override val title: String = "행사 상품",
            override val filters: List<EventType> = EventType.values().toList()
        ) : Filter<EventType>
    }
}
