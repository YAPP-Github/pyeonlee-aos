package com.peonlee.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.peonlee.core.ui.adapter.MultiTypeListAdapter
import com.peonlee.core.ui.databinding.ListItemBaseDividerBinding
import com.peonlee.core.ui.databinding.ListItemBaseTitleBinding
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.core.ui.viewholder.divider.BaseDividerViewHolder
import com.peonlee.core.ui.viewholder.title.BaseTitleViewHolder
import com.peonlee.model.MainHomeListItem
import com.peonlee.model.MainHomeViewType

class HomeAdapter : MultiTypeListAdapter<MainHomeListItem, MainHomeViewType>() {
    override fun onCreateViewHolder(
        viewType: MainHomeViewType,
        parent: ViewGroup
    ): CommonViewHolder<MainHomeListItem> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            MainHomeViewType.TITLE -> BaseTitleViewHolder(ListItemBaseTitleBinding.inflate(inflater, parent, false))
            MainHomeViewType.DIVIDER -> BaseDividerViewHolder(ListItemBaseDividerBinding.inflate(inflater, parent, false))
        }
    }
}
