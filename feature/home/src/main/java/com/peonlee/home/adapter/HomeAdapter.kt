package com.peonlee.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.peonlee.core.ui.Navigator
import com.peonlee.core.ui.adapter.MultiTypeListAdapter
import com.peonlee.core.ui.databinding.ListItemCurrentEventBinding
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.core.ui.viewholder.event.EventViewHolder
import com.peonlee.home.adapter.viewholder.button.ButtonViewHolder
import com.peonlee.home.adapter.viewholder.divider.HomeDividerViewHolder
import com.peonlee.home.adapter.viewholder.product.ConditionalProductsViewHolder
import com.peonlee.home.adapter.viewholder.product.EventByStoresViewHolder
import com.peonlee.home.adapter.viewholder.review.RecentReviewViewHolder
import com.peonlee.home.adapter.viewholder.title.TitleViewHolder
import com.peonlee.home.databinding.ListItemButtonBinding
import com.peonlee.home.databinding.ListItemConditionalProductsBinding
import com.peonlee.home.databinding.ListItemEventStoresBinding
import com.peonlee.home.databinding.ListItemHomeDividerBinding
import com.peonlee.home.databinding.ListItemRecentReviewBinding
import com.peonlee.home.databinding.ListItemTitleBinding
import com.peonlee.home.model.button.ButtonType
import com.peonlee.model.MainHomeListItem
import com.peonlee.model.MainHomeViewType
import com.peonlee.model.type.SortType
import com.peonlee.model.type.StoreType

class HomeAdapter(
    private val navigator: Navigator,
    private val moveToConditionExplore: (SortType) -> Unit,
    private val moveToStoreExplore: (StoreType) -> Unit,
    private val onClickButton: (ButtonType) -> Unit
) : MultiTypeListAdapter<MainHomeListItem, MainHomeViewType>() {
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
                navigator,
                moveToConditionExplore
            )

            MainHomeViewType.RECENT_REVIEW -> RecentReviewViewHolder(navigator, ListItemRecentReviewBinding.inflate(inflater, parent, false))
            MainHomeViewType.EVENT_BY_STORE -> EventByStoresViewHolder(
                ListItemEventStoresBinding.inflate(inflater, parent, false),
                navigator,
                moveToStoreExplore
            )

            MainHomeViewType.BUTTON -> ButtonViewHolder(
                ListItemButtonBinding.inflate(inflater, parent, false),
                onClickButton
            )

            MainHomeViewType.EVENT -> EventViewHolder(
                ListItemCurrentEventBinding.inflate(inflater, parent, false),
                navigator
            )
        }
    }
}
