package com.peonlee

import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.settings.databinding.ItemTermsBinding

class SettingViewHolder(
    private val binding: ItemTermsBinding,
    private val onClickEvent: (SettingUiModel) -> Unit
) : CommonViewHolder<SettingUiModel>(binding) {
    override fun onBindView(item: SettingUiModel) {
        with(binding) {
            tvTermsTitle.text = item.termTitle
            itemView.setOnClickListener { onClickEvent(item) }
        }
    }
}
