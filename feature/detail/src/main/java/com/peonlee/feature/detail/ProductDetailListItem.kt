package com.peonlee.feature.detail

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.peonlee.core.ui.R
import com.peonlee.model.ListItem

sealed class ProductDetailListItem(override val viewType: ViewType) : ListItem {

    enum class ViewType {
        PRODUCT,
        RATING,
        REVIEW_HEADER,
        NONE_REVIEW,
        REVIEW,
        DIVIDER
    }

    data class Product(
        override val id: Long,
        val imageUrl: String,
        val productName: String,
        val price: Int,
        val upvoteRate: Int,
        val reviewCount: Int,
        val eventList: List<Event>
    ) : ProductDetailListItem(ViewType.PRODUCT)

    // todo dummy domain model
    data class Event(
        val retailerType: RetailerType,
        val promotionType: PromotionType
    )

    enum class RetailerType(@DrawableRes val imageRes: Int) {
        GS(R.drawable.ic_store_gs25),
        CU(R.drawable.ic_store_cu),
        SEVEN_ELEVEN(R.drawable.ic_store_seveneleven)
    }

    enum class PromotionType(@StringRes val stringRes: Int) {
        ONE_PLUS_ONE(R.string.promotion_one_plus_one),
        TWO_PLUS_ONE(R.string.promotion_two_plus_one)
    }

    data class Rating(
        override val id: Long,
        val rateCount: Int,
        val upvoteRate: Int,
        val downvoteRate: Int
    ) : ProductDetailListItem(ViewType.RATING)

    data class ReviewHeader(
        override val id: Long,
        val reviewCount: Int
    ) : ProductDetailListItem(ViewType.REVIEW_HEADER)

    data class NoneReview(
        override val id: Long
    ) : ProductDetailListItem(ViewType.NONE_REVIEW)

    data class Review(
        override val id: Long,
        val nickname: String,
        val writeDate: String,
        val isUpvote: Boolean,
        val reviewText: String,
        val isLike: Boolean,
        val likeCount: Int,
        val isMine: Boolean
    ) : ProductDetailListItem(ViewType.REVIEW)

    data class Divider(
        override val id: Long
    ) : ProductDetailListItem(ViewType.DIVIDER)
}
