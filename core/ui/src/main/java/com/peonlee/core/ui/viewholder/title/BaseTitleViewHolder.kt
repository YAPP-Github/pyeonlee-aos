package com.peonlee.core.ui.viewholder.title

import com.peonlee.core.ui.databinding.ListItemBaseTitleBinding
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.model.title.BaseTitleUiModel

class BaseTitleViewHolder(
    private val binding: ListItemBaseTitleBinding
) : CommonViewHolder<BaseTitleUiModel>(binding) {
    override fun onBindView(item: BaseTitleUiModel) {
        binding.apply {
            tvTitle.text = item.title
        }
    }
}
