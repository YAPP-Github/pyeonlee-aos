package com.peonlee.evaluate

import coil.load
import com.peonlee.core.ui.extensions.toFormattedMoney
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.data.model.Content
import com.peonlee.evaluate.databinding.ListItemEvaluateBinding

class EvaluateViewHolder(
    private val binding: ListItemEvaluateBinding
) : CommonViewHolder<Content>(binding) {
    override fun onBindView(item: Content) {
        binding.apply {
            tvEvaluateProductTitle.text = item.productName
            tvEvaluateProductPrice.text = item.price.toFormattedMoney()
            ivEvaluateProductImage.load(item.imageUrl)
        }
    }
}
