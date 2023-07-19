package com.peonlee.feature.detail

import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import com.peonlee.common.util.TimeUtil
import com.peonlee.core.ui.R
import com.peonlee.core.ui.extensions.getStringWithArgs
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.data.model.LikeType
import com.peonlee.feature.detail.databinding.ListItemCommentBinding
import java.time.LocalDateTime

class CommentViewHolder(
    private val showReviewManageDialog: (ProductDetailListItem.Review) -> Unit,
    private val binding: ListItemCommentBinding
) : CommonViewHolder<ProductDetailListItem.Review>(binding) {
    init {
        binding.ivManageReview.setOnClickListener {
            getItem {
                showReviewManageDialog(it)
            }
        }
    }

    override fun onBindView(item: ProductDetailListItem.Review) = with(binding) {
        tvComment.text = item.reviewText
        tvUserNameAndDate.text = getStringWithArgs(
            R.string.item_recent_review_user_and_date,
            item.nickname,
            TimeUtil.getDuration(
                itemView.context,
                LocalDateTime.parse(item.writeDate)
            )
        )

        tvLikeCount.setCompoundDrawablesWithIntrinsicBounds(
            ResourcesCompat.getDrawable(
                binding.root.resources,
                if (item.isLike) {
                    R.drawable.ic_filled_heart
                } else {
                    R.drawable.ic_empty_heart
                },
                null
            ),
            null,
            null,
            null
        )
        tvLikeCount.text = if (item.likeCount > 0) item.likeCount.toString() else ""
        layoutThumbsDown.isVisible = item.likeType == LikeType.DISLIKE
        layoutThumbsUp.isVisible = item.likeType == LikeType.LIKE
        tvMyReviewBadge.isVisible = item.isMine
        ivManageReview.isVisible = item.isMine
        return@with
    }
}
