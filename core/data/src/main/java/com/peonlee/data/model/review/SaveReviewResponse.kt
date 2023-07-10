package com.peonlee.data.model.review

import kotlinx.serialization.Serializable

/**
 * 리뷰 작성 API Response
 */
@Serializable
data class SaveReviewResponse(
    val productCommentId: Int, // 작성한 리뷰 id
    val productId: Int, // 작성한 상품 id
    val memberId: Int, // 작성한 사용자 id
    val content: String // 작성한 리뷰 내용
)
