package com.peonlee.core.ui.viewholder.product

import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
import androidx.core.view.isVisible
import coil.load
import com.peonlee.common.ext.toFormattedMoney
import com.peonlee.core.ui.Navigator
import com.peonlee.core.ui.R
import com.peonlee.core.ui.databinding.ListItemProductBinding
import com.peonlee.core.ui.extensions.getStringWithArgs
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.model.product.ProductUiModel

class ProductViewHolder(
    private val layoutParams: LayoutParams,
    val binding: ListItemProductBinding,
    val navigator: Navigator
) : CommonViewHolder<ProductUiModel>(binding) {
    init {
        binding.root.setOnClickListener {
            getItem {
                navigator.navigateToProductDetail(binding.root.context, it.id)
            }
        }
    }

    override fun onBindView(item: ProductUiModel) = with(binding) {
        root.layoutParams = layoutParams
        tvProductName.text = item.name
        ivProductImage.load(item.imageUrl)
        tvProductPrice.text = item.price.toFormattedMoney()
        tvProductRecommended.text = getStringWithArgs(
            R.string.item_product_recommended_percentage,
            item.percentage
        )
        tvReviewCount.text = getStringWithArgs(
            R.string.item_product_review_count,
            item.reviewCnt
        )
        tvProductEvent.isVisible = item.isEvent
    }
}
