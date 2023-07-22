package com.peonlee.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    val content: String,
    val createdAt: String,
    val isOwner: Boolean,
    val likeCount: Int,
    val liked: Boolean,
    val memberId: Int,
    val memberNickName: String,
    val productCommentId: Int,
    val productId: Int,
    val productLikeType: LikeType
)

enum class LikeType {
    LIKE,
    DISLIKE,
    NONE
}
