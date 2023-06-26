package com.peonlee.feature.detail

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
        val imageUrl: String,
        val title: String
    )

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
        val likeCount: Int
    ) : ProductDetailListItem(ViewType.REVIEW)

    data class Divider(
        override val id: Long
    ) : ProductDetailListItem(ViewType.DIVIDER)
}
