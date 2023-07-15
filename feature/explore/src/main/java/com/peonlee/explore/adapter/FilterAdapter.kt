package com.peonlee.explore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.peonlee.core.ui.adapter.SingleTypeListAdapter
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.explore.adapter.viewholder.FilterViewHolder
import com.peonlee.explore.databinding.ItemFilterSelectorBinding
import com.peonlee.explore.model.BaseFilter

class FilterAdapter(
    private val onClickEvent: (BaseFilter) -> Unit
) : SingleTypeListAdapter<BaseFilter>({ it }) {
    override fun onCreateViewHolder(parent: ViewGroup): CommonViewHolder<BaseFilter> {
        return FilterViewHolder(
            ItemFilterSelectorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickEvent
        )
    }
}
