package com.peonlee.data.model.home

import com.peonlee.data.model.LikeType
import kotlinx.serialization.Serializable

@Serializable
data class HomeComment(
    val content: String,
    val createdAt: String,
    val likeCount: Int,
    val memberId: Int,
    val memberNickName: String,
    val productBrandName: String,
    val productCommentId: Int,
    val productId: Int,
    val productImageUrl: String,
    val productLikeType: LikeType,
    val productName: String
)
