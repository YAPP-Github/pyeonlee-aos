package com.peonlee.home.adapter.viewholder.title

import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.home.databinding.ListItemTitleBinding
import com.peonlee.home.model.title.TitleUiModel

class TitleViewHolder(
    private val binding: ListItemTitleBinding
) : CommonViewHolder<TitleUiModel>(binding) {
    override fun onBindView(item: TitleUiModel) = with(binding) {
        tvTitle.text = item.title
    }
}
