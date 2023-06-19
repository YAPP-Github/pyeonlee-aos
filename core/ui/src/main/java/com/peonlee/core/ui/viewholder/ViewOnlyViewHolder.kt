package com.peonlee.core.ui.viewholder

import androidx.viewbinding.ViewBinding

open class ViewOnlyViewHolder(binding: ViewBinding) : CommonViewHolder<Nothing>(binding) {
    final override fun onBindView(item: Nothing) = Unit
}
