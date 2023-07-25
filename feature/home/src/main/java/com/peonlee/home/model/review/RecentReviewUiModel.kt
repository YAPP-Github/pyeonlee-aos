package com.peonlee.home.model.review

import com.peonlee.data.model.LikeType
import com.peonlee.data.model.home.HomeComment
import com.peonlee.model.MainHomeListItem
import com.peonlee.model.MainHomeViewType

/**
 * 최근 리뷰 Model
 */
data class RecentReviewUiModel(
    override val id: Long,
    override val viewType: MainHomeViewType = MainHomeViewType.RECENT_REVIEW,
    val imageUrl: String,
    val productId: Int,
    val productName: String, // 상품 정보
    val likeType: LikeType, // 평가(like, unlike)
    val content: String?, // 댓글(없을 수도 있음)
    val userName: String, // 작성한 사용자 닉네임
    val updateDate: String // 해당 리뷰를 작성한 날짜
) : MainHomeListItem

fun HomeComment.toUiModel() = RecentReviewUiModel(
    id = productCommentId.toLong(),
    imageUrl = productImageUrl,
    productId = productId,
    productName = productName,
    likeType = productLikeType,
    content = content,
    userName = memberNickName,
    updateDate = createdAt
)
