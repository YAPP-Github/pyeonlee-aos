package com.peonlee.home.adapter.viewholder.review

import androidx.core.view.isGone
import androidx.core.view.isInvisible
import coil.load
import com.peonlee.common.util.TimeUtil
import com.peonlee.core.ui.Navigator
import com.peonlee.core.ui.designsystem.chip.MediumChip
import com.peonlee.core.ui.extensions.getString
import com.peonlee.core.ui.extensions.getStringWithArgs
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.data.model.LikeType
import com.peonlee.home.databinding.ListItemRecentReviewBinding
import com.peonlee.home.model.review.RecentReviewUiModel
import java.time.LocalDateTime
import com.peonlee.core.ui.R as UiResource
import com.peonlee.home.R as homeResource

/**
 * 최근 리뷰 View Holder
 */
class RecentReviewViewHolder(
    private val navigator: Navigator,
    private val binding: ListItemRecentReviewBinding
) : CommonViewHolder<RecentReviewUiModel>(binding) {
    init {
        binding.root.setOnClickListener {
            getItem {
                navigator.navigateToProductDetail(binding.root.context, it.productId)
            }
        }
    }

    override fun onBindView(item: RecentReviewUiModel) =
        with(binding) {
            ivProduct.load(item.imageUrl)
            // 상품 이름
            tvProductName.text = item.productName
            // 리뷰
            tvComment.text = item.content
            // 리뷰 작성자 & 작성 날짜
            tvUserNameAndDate.text = getStringWithArgs(
                homeResource.string.item_recent_review_user_and_date,
                item.userName,
                TimeUtil.getDuration(
                    itemView.context,
                    LocalDateTime.parse(item.updateDate)
                )
            )
            binding.chipRecommended.isInvisible = item.likeType == LikeType.NONE
            // 추천/비추천 chip
            when (item.likeType) {
                LikeType.LIKE -> setRecommendedChip(binding.chipRecommended)
                LikeType.DISLIKE -> setNoneRecommendedChip(binding.chipRecommended)
                LikeType.NONE -> Unit
            }
        }
}

// 추천 chip
private fun RecentReviewViewHolder.setRecommendedChip(
    chip: MediumChip
) {
    chip.text = getString(homeResource.string.item_recent_review_recommended)
}

// 비추천 chip
private fun RecentReviewViewHolder.setNoneRecommendedChip(
    chip: MediumChip
) {
    chip.apply {
        text = getString(homeResource.string.item_recent_review_none_recommended)
        setBackgroundTint(UiResource.color.system_r40)
        setIcon(UiResource.drawable.ic_thumbs_down)
        setIconTint(UiResource.color.system_r100)
        setTextColor(UiResource.color.system_r100)
    }
}
