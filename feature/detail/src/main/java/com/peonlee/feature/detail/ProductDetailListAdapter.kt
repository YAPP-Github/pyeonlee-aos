package com.peonlee.feature.detail

import android.view.LayoutInflater
import android.view.View.generateViewId
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.doOnAttach
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import coil.load
import com.peonlee.common.ext.toFormattedMoney
import com.peonlee.common.util.TimeUtil
import com.peonlee.core.ui.R
import com.peonlee.core.ui.adapter.MultiTypeListAdapter
import com.peonlee.core.ui.extensions.getString
import com.peonlee.core.ui.extensions.getStringWithArgs
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.core.ui.viewholder.ViewOnlyViewHolder
import com.peonlee.feature.detail.databinding.ListItemDetailProductBinding
import com.peonlee.feature.detail.databinding.ListItemDividerBinding
import com.peonlee.feature.detail.databinding.ListItemEventBinding
import com.peonlee.feature.detail.databinding.ListItemNoneReviewBinding
import com.peonlee.feature.detail.databinding.ListItemRatingBinding
import com.peonlee.feature.detail.databinding.ListItemReviewBinding
import com.peonlee.feature.detail.databinding.ListItemReviewHeaderBinding
import java.time.LocalDateTime

class ProductDetailListAdapter(
    private val navigateToEditReview: () -> Unit,
    private val showReviewManageDialog: (ProductDetailListItem.Review) -> Unit
) : MultiTypeListAdapter<ProductDetailListItem, ProductDetailListItem.ViewType>() {
    override fun onCreateViewHolder(viewType: ProductDetailListItem.ViewType, parent: ViewGroup): CommonViewHolder<ProductDetailListItem> {
        return when (viewType) {
            ProductDetailListItem.ViewType.PRODUCT -> ProductViewHolder(
                ListItemDetailProductBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            ProductDetailListItem.ViewType.SCORE -> RatingViewHolder(ListItemRatingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            ProductDetailListItem.ViewType.REVIEW_HEADER -> ReviewHeaderViewHolder(
                ListItemReviewHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            ProductDetailListItem.ViewType.NONE_REVIEW -> NoneReviewViewHolder(
                ListItemNoneReviewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            ProductDetailListItem.ViewType.REVIEW -> ReviewViewHolder(ListItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            ProductDetailListItem.ViewType.DIVIDER -> DividerViewHolder(ListItemDividerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    private inner class ProductViewHolder(private val binding: ListItemDetailProductBinding) : CommonViewHolder<ProductDetailListItem.Product>(binding) {
        init {
            binding.root.doOnAttach {
                getItem { item ->
                    if (item.eventList.isEmpty()) {
                        binding.tvEventTitle.isGone = true
                        binding.flowEvent.isGone = true
                        return@getItem
                    }
                    item.eventList.forEachIndexed { index, event ->
                        val eventView = ListItemEventBinding.inflate(LayoutInflater.from(binding.root.context), binding.root, false).apply {
                            tvEventDes.text = getString(event.promotionType.stringRes)
                            ivStoreIcon.load(event.retailerType.imageRes)
                            root.id = generateViewId()
                            binding.root.addView(root, index)
                        }
                        binding.flowEvent.addView(eventView.root)
                    }
                }
            }
        }

        override fun onBindView(item: ProductDetailListItem.Product) = with(binding) {
            ivProductImage.load(item.imageUrl)
            tvProductName.text = item.productName
            tvProductPrice.text = item.price.toFormattedMoney()
            tvProductRecommended.text = getStringWithArgs(
                R.string.item_product_recommended_percentage,
                item.upvoteRate
            )
            tvReviewCount.text = getStringWithArgs(
                R.string.item_product_review_count,
                item.reviewCount
            )
            return@with
        }
    }

    private inner class DividerViewHolder(binding: ListItemDividerBinding) : ViewOnlyViewHolder(binding)

    private inner class ReviewHeaderViewHolder(private val binding: ListItemReviewHeaderBinding) :
        CommonViewHolder<ProductDetailListItem.ReviewHeader>(binding) {
        init {
            binding.tvShowMoreButton.setOnClickListener {
                getItem {
                    // TODO
                }
            }
        }

        override fun onBindView(item: ProductDetailListItem.ReviewHeader) = with(binding) {
            tvReviewCount.text = getStringWithArgs(com.peonlee.feature.detail.R.string.count, item.reviewCount)
            return@with
        }
    }

    private inner class NoneReviewViewHolder(private val binding: ListItemNoneReviewBinding) : CommonViewHolder<ProductDetailListItem.NoneReview>(binding) {
        init {
            binding.tvWriteReviewButton.setOnClickListener {
                getItem {
                    navigateToEditReview()
                }
            }
        }

        override fun onBindView(item: ProductDetailListItem.NoneReview) = Unit
    }

    private inner class RatingViewHolder(private val binding: ListItemRatingBinding) : CommonViewHolder<ProductDetailListItem.Score>(binding) {
        override fun onBindView(item: ProductDetailListItem.Score) = with(binding) {
            tvTotalRateCount.text = getStringWithArgs(com.peonlee.feature.detail.R.string.rate_count, item.rateCount)
            tvThumbsUpPercent.text = getStringWithArgs(
                R.string.item_product_recommended_percentage,
                item.upvoteRate
            )
            tvThumbsDownPercent.text = getStringWithArgs(
                R.string.item_product_recommended_percentage,
                item.downvoteRate
            )

            vThumbsUpRate.updateLayoutParams<LinearLayout.LayoutParams> {
                weight = item.upvoteRate.toFloat()
            }
            vThumbsDownRate.updateLayoutParams<LinearLayout.LayoutParams> {
                weight = item.downvoteRate.toFloat()
            }
        }
    }

    private inner class ReviewViewHolder(private val binding: ListItemReviewBinding) : CommonViewHolder<ProductDetailListItem.Review>(binding) {
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
                    LocalDateTime.now()
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
            layoutThumbsDown.isVisible = item.isUpvote.not()
            layoutThumbsUp.isVisible = item.isUpvote
            tvMyReviewBadge.isVisible = item.isMine
            ivManageReview.isVisible = item.isMine
            return@with
        }
    }
}
