package com.peonlee.product.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.peonlee.core.ui.adapter.SingleTypeListAdapter
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.product.adapter.viewholder.FilterViewHolder
import com.peonlee.product.databinding.ItemFilterSelectorBinding
import com.peonlee.product.model.BaseFilter

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
