package com.peonlee.explore.adapter

import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.explore.databinding.ItemFilterSelectorBinding
import com.peonlee.explore.model.BaseFilter

class FilterViewHolder(
    private val binding: ItemFilterSelectorBinding,
    private val onClickEvent: (BaseFilter) -> Unit
) : CommonViewHolder<BaseFilter>(binding) {
    override fun onBindView(item: BaseFilter) {
        with(binding.root) {
            text = item.title
            if (item.isSelected) setFillColor() else setCancelColor()
            icon = item.iconId
            setOnClickListener { onClickEvent(item) }
        }
    }
}
