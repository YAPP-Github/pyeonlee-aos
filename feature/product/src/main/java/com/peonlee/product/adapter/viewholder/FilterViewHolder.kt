package com.peonlee.product.adapter.viewholder

import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.product.databinding.ItemFilterSelectorBinding
import com.peonlee.product.model.BaseFilter

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
