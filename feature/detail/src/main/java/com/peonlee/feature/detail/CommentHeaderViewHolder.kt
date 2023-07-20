package com.peonlee.feature.detail

import androidx.core.view.isVisible
import com.peonlee.core.ui.extensions.getStringWithArgs
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.feature.detail.databinding.ListItemCommentHeaderBinding

class CommentHeaderViewHolder(
    private val binding: ListItemCommentHeaderBinding,
    private val navigateToProductComments: (() -> Unit)? = null
) :
    CommonViewHolder<ProductDetailListItem.ReviewHeader>(binding) {
    init {
        binding.tvShowMoreButton.isVisible = navigateToProductComments != null
        binding.tvShowMoreButton.setOnClickListener {
            navigateToProductComments?.invoke()
        }
    }

    override fun onBindView(item: ProductDetailListItem.ReviewHeader) = with(binding) {
        tvReviewCount.text = getStringWithArgs(com.peonlee.feature.detail.R.string.count, item.reviewCount)
        return@with
    }
}
