package com.peonlee.data.model.review

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 리뷰 작성 API Request
 */
@Serializable
data class SaveReviewRequest(
    @SerialName("content")
    val review: String
)
