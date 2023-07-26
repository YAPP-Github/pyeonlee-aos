package com.peonlee.evaluate

import coil.load
import com.peonlee.common.ext.toFormattedMoney
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.data.model.Product
import com.peonlee.evaluate.databinding.ListItemEvaluateBinding

class EvaluateViewHolder(
    private val binding: ListItemEvaluateBinding,
    private val onClickEvent: (Int) -> Unit
) : CommonViewHolder<Product>(binding) {
    override fun onBindView(item: Product) {
        binding.apply {
            tvEvaluateProductTitle.text = item.productName
            tvEvaluateProductPrice.text = item.price.toFormattedMoney()
            ivEvaluateProductImage.load(item.imageUrl)
            itemView.setOnClickListener { onClickEvent.invoke(item.productId) }
        }
    }
}
