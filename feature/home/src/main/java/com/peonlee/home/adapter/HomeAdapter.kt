package com.peonlee.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.peonlee.core.ui.Navigator
import com.peonlee.core.ui.adapter.MultiTypeListAdapter
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.home.adapter.viewholder.divider.HomeDividerViewHolder
import com.peonlee.home.adapter.viewholder.product.ConditionalProductsViewHolder
import com.peonlee.home.adapter.viewholder.product.EventByStoresViewHolder
import com.peonlee.home.adapter.viewholder.review.RecentReviewViewHolder
import com.peonlee.home.adapter.viewholder.title.TitleViewHolder
import com.peonlee.home.databinding.ListItemConditionalProductsBinding
import com.peonlee.home.databinding.ListItemEventStoresBinding
import com.peonlee.home.databinding.ListItemHomeDividerBinding
import com.peonlee.home.databinding.ListItemRecentReviewBinding
import com.peonlee.home.databinding.ListItemTitleBinding
import com.peonlee.model.MainHomeListItem
import com.peonlee.model.MainHomeViewType

class HomeAdapter(private val navigator: Navigator) : MultiTypeListAdapter<MainHomeListItem, MainHomeViewType>() {
    override fun onCreateViewHolder(
        viewType: MainHomeViewType,
        parent: ViewGroup
    ): CommonViewHolder<MainHomeListItem> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            MainHomeViewType.TITLE -> TitleViewHolder(ListItemTitleBinding.inflate(inflater, parent, false))
            MainHomeViewType.DIVIDER -> HomeDividerViewHolder(ListItemHomeDividerBinding.inflate(inflater, parent, false))
            MainHomeViewType.CONDITIONAL_PRODUCTS -> ConditionalProductsViewHolder(
                ListItemConditionalProductsBinding.inflate(inflater, parent, false),
                navigator
            )

            MainHomeViewType.RECENT_REVIEW -> RecentReviewViewHolder(ListItemRecentReviewBinding.inflate(inflater, parent, false))
            MainHomeViewType.EVENT_BY_STORE -> EventByStoresViewHolder(ListItemEventStoresBinding.inflate(inflater, parent, false), navigator)
        }
    }
}
