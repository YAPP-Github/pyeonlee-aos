package com.peonlee.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    val commentLikeCount: Int,
    val content: String,
    val createdAt: String,
    val isOwner: Boolean,
    val likeType: LikeType,
    val liked: Boolean,
    val member: Member,
    val productCommentId: Int,
    val productId: Int
)

enum class LikeType {
    LIKE,
    DISLIKE,
    NONE
}
