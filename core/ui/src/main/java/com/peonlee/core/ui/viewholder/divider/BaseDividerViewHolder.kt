package com.peonlee.core.ui.viewholder.divider

import com.peonlee.core.ui.databinding.ListItemBaseDividerBinding
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.model.divider.BaseDividerUiModel

class BaseDividerViewHolder(
    private val binding: ListItemBaseDividerBinding
) : CommonViewHolder<BaseDividerUiModel>(binding) {
    override fun onBindView(item: BaseDividerUiModel) {}
}
