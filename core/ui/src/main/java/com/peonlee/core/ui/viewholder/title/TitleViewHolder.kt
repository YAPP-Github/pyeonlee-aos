package com.peonlee.core.ui.viewholder.title

import com.peonlee.core.ui.databinding.ListItemTitleBinding
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.model.title.TitleUiModel

class TitleViewHolder(
    private val binding: ListItemTitleBinding
) : CommonViewHolder<TitleUiModel>(binding) {
    override fun onBindView(item: TitleUiModel) = with(binding) {
        tvTitle.text = item.title
    }
}
