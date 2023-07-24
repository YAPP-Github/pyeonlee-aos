package com.peonlee

import android.view.LayoutInflater
import android.view.ViewGroup
import com.peonlee.core.ui.adapter.SingleTypeListAdapter
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.settings.databinding.ItemTermsBinding

class SettingAdapter(
    private val onClickEvent: (SettingUiModel) -> Unit
) : SingleTypeListAdapter<SettingUiModel>({ it.termTitle }) {
    override fun onCreateViewHolder(parent: ViewGroup): CommonViewHolder<SettingUiModel> {
        return SettingViewHolder(
            ItemTermsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickEvent
        )
    }
}


