package com.peonlee.data.model.review

import kotlinx.serialization.Serializable

/**
 * 리뷰 작성 API Response
 */
@Serializable
data class SaveReviewResponse(
    val content: String
)
