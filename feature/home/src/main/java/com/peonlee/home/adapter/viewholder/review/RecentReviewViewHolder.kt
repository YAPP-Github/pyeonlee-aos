package com.peonlee.home.adapter.viewholder.review

import com.peonlee.core.ui.extensions.getStringWithArgs
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.home.R as homeResource
import com.peonlee.core.ui.R as UiResource
import com.peonlee.home.databinding.ListItemRecentReviewBinding
import com.peonlee.home.model.review.RecentReviewUiModel

/**
 * 최근 리뷰 View Holder
 */
class RecentReviewViewHolder(
    private val binding: ListItemRecentReviewBinding
) : CommonViewHolder<RecentReviewUiModel>(binding) {
    override fun onBindView(item: RecentReviewUiModel) =
        with(binding) {
            // 상품 이름
            tvProductName.text = item.product.name
            // 리뷰
            item.comment?.let { tvComment.text = it }
            // 리뷰 작성자 & 작성 날짜
            tvUserNameAndDate.text = getStringWithArgs(
                homeResource.string.item_recent_review_user_and_date,
                item.userName, item.updateDate.monthValue
            )
        }
}
