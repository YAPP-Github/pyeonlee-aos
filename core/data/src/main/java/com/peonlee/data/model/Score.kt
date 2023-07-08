package com.peonlee.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Score(
    val dislikeCount: Int,
    val dislikeRatio: Int,
    val likeCount: Int,
    val likeRatio: Int,
    val totalCount: Int
)
