package com.peonlee.data.model.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 홈 화면에 사용 되는 리뷰 Response
 */
@Serializable
data class HomeReviewResponse(
    @SerialName("productCommentId")
    val productCommentId: Int, // 리뷰 id
    @SerialName("productId")
    val productId: Int, // 해당 리뷰가 작성된 상품 id
    @SerialName("memberId")
    val memberId: Int, // 리뷰를 작성한 사용자 id
    @SerialName("content")
    val content: String // 작성한 리뷰
)
