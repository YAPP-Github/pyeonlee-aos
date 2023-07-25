package com.peonlee.data.model.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 사용자 정보 요청 Response
 */
@Serializable
data class UserResponse(
    @SerialName("memberId")
    val memberId: Int, // 사용자 id
    @SerialName("nickname")
    val nickname: String, // 사용자 닉네임
    @SerialName("productLikeCount")
    val productLikeCount: Int, // 평가한 상품 개수
    @SerialName("productCommentCount")
    val productCommentCount: Int // 작성한 리뷰 개수
)
