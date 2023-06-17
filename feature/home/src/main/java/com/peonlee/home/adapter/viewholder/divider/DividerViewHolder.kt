package com.peonlee.home.adapter.viewholder.divider

import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.home.databinding.ListItemDividerBinding
import com.peonlee.home.model.divider.DividerUiModel

class DividerViewHolder(
    private val binding: ListItemDividerBinding
) : CommonViewHolder<DividerUiModel>(binding) {
    override fun onBindView(item: DividerUiModel) {}
}
