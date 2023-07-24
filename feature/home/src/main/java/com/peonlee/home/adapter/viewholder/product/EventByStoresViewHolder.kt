package com.peonlee.home.adapter.viewholder.product

import android.view.LayoutInflater
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.peonlee.core.ui.Navigator
import com.peonlee.core.ui.designsystem.selector.MediumSelector
import com.peonlee.core.ui.extensions.getStringWithArgs
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.home.adapter.ProductsByStoreAdapter
import com.peonlee.home.databinding.ItemStoreSelectorBinding
import com.peonlee.home.databinding.ListItemEventStoresBinding
import com.peonlee.home.model.product.EventByStoresUiModel
import com.peonlee.model.type.StoreType
import com.peonlee.core.ui.R as UiResource
import com.peonlee.home.R as HomeResource

class EventByStoresViewHolder(
    private val binding: ListItemEventStoresBinding,
    navigator: Navigator,
    private val moveToStoreExplore: (StoreType) -> Unit
) : CommonViewHolder<EventByStoresUiModel>(binding) {
    private var productsByStoreAdapter = ProductsByStoreAdapter(navigator)
    private var currentStoreType: StoreType = StoreType.CU

    private val onTabSelectedListener = object : OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab?) {}
        override fun onTabSelected(tab: TabLayout.Tab?) {
            tab?.let {
                val store = StoreType.values()[it.position]
                currentStoreType = store
                (it.customView as? MediumSelector)?.applyBackground(getBackgroundByStore(store))
                binding.btnMoreProducts.text = getStringWithArgs(
                    id = HomeResource.string.item_conditional_products_button_text,
                    store.storeName
                )
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
            tab?.let {
                (it.customView as? MediumSelector)?.applyBackground(
                    UiResource.drawable.bg_white_outline_radius_17dp
                )
            }
        }
    }

    init {
        with(binding) {
            pagerProducts.adapter = productsByStoreAdapter
            TabLayoutMediator(layoutStoreTab, pagerProducts) { tab, position ->
                val store = StoreType.values()[position]
                val tabItem = ItemStoreSelectorBinding.inflate(
                    LayoutInflater.from(itemView.context)
                )
                tabItem.root.text = store.storeName
                tabItem.root.setIcon(getIconByStore(store))
                tab.customView = tabItem.root
            }.attach()
            /**
             * viewHolder 가 생성될 때, 1번만 onTabSelectedListener가 등록되도록 수정
             * [Comment](https://github.com/YAPP-Github/22nd-Android-Team-1-Android/pull/32#discussion_r1244692924)
             */
            layoutStoreTab.addOnTabSelectedListener(onTabSelectedListener)
        }
    }

    override fun onBindView(item: EventByStoresUiModel) {
        productsByStoreAdapter.submitList(item.stores)
        binding.btnMoreProducts.setOnClickListener {
            moveToStoreExplore(currentStoreType)
        }
    }

    companion object {
        private fun getBackgroundByStore(storeType: StoreType): Int = when (storeType) {
            StoreType.CU -> UiResource.drawable.bg_store_outline_cu_radius_17dp
            StoreType.GS25 -> UiResource.drawable.bg_store_outline_gs25_radius_17dp
            StoreType.SEVEN -> UiResource.drawable.bg_store_outline_seveneleven_radius_17dp
        }

        private fun getIconByStore(storeType: StoreType): Int = when (storeType) {
            StoreType.CU -> UiResource.drawable.ic_store_cu
            StoreType.GS25 -> UiResource.drawable.ic_store_gs25
            StoreType.SEVEN -> UiResource.drawable.ic_store_seveneleven
        }
    }
}
