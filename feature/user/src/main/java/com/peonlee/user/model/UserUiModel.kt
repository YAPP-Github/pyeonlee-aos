package com.peonlee.user.model

import com.peonlee.data.model.user.UserResponse

/**
 * 사용자 정보
 */
data class UserUiModel(
    val memberId: Int = -1, // 사용자 id
    val nickname: String = "", // 사용자 닉네임
    val productLikeCount: Int = 0, // 평가한 상품 개수
    val productCommentCount: Int = 0 // 작성한 리뷰 개수
)

fun UserResponse.toUiModel() = UserUiModel(
    memberId = memberId,
    nickname = nickname,
    productLikeCount = productLikeCount,
    productCommentCount = productCommentCount
)
