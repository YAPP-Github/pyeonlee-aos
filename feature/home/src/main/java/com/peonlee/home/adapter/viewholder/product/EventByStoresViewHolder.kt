package com.peonlee.home.adapter.viewholder.product

import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.home.databinding.ListItemEventStoresBinding
import com.peonlee.home.model.product.EventByStoresUiModel

class EventByStoresViewHolder(
    private val binding: ListItemEventStoresBinding
): CommonViewHolder<EventByStoresUiModel>(binding) {

    private var tabLayoutMediator: TabLayoutMediator? = null
    private val onTabSelectedListener = object : OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab?) {}
        override fun onTabSelected(tab: TabLayout.Tab?) {}
        override fun onTabUnselected(tab: TabLayout.Tab?) {}
    }

    override fun onBindView(item: EventByStoresUiModel) {
    }
}
