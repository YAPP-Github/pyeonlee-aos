package com.peonlee.core.ui.viewholder.product

import com.peonlee.core.ui.R
import com.peonlee.core.ui.databinding.ListItemProductBinding
import com.peonlee.core.ui.extensions.getStringWithArgs
import com.peonlee.core.ui.extensions.toFormattedMoney
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.model.product.ProductUiModel

class ProductViewHolder(
    private val binding: ListItemProductBinding
) : CommonViewHolder<ProductUiModel>(binding) {
    override fun onBindView(item: ProductUiModel) = with(binding) {
        tvProductName.text = item.name
        tvProductPrice.text = item.price.toFormattedMoney()
        tvProductRecommended.text = getStringWithArgs(
            R.string.item_product_recommended_percentage,
            item.percentage
        )
        tvReviewCount.text = getStringWithArgs(
            R.string.item_product_review_count,
            item.reviewCnt
        )
    }
}

