package com.peonlee.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductDetail(
    val brandName: String,
    val categoryType: String,
    val imageUrl: String,
    val isPbProduct: Boolean,
    val name: String,
    val price: Int,
    val productId: Int,
    val promotionList: List<Promotion>,
    val score: Score,
    val productRatingType: ProductRatingType
)

enum class ProductRatingType {
    LIKE,
    DISLIKE,
    NONE
}
