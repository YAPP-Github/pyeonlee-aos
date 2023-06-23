package com.peonlee.home.model.review

import com.peonlee.model.MainHomeListItem
import com.peonlee.model.MainHomeViewType
import com.peonlee.model.product.PRODUCTS_TEST_DOUBLE
import com.peonlee.model.product.ProductUiModel
import java.time.LocalDateTime

/**
 * 최근 리뷰 Model
 */
data class RecentReviewUiModel(
    override val id: Long,
    override val viewType: MainHomeViewType = MainHomeViewType.RECENT_REVIEW,
    val product: ProductUiModel, // 상품 정보
    val recommended: Boolean, // 평가(like, unlike)
    val comment: String?, // 댓글(없을 수도 있음)
    val userName: String, // 작성한 사용자 닉네임
    val updateDate: LocalDateTime // 해당 리뷰를 작성한 날짜
) : MainHomeListItem

val RECENT_REVIEW = (1..10).map {
    RecentReviewUiModel(
        id = it.toLong(),
        product = PRODUCTS_TEST_DOUBLE.first(),
        recommended = it % 2 == 0,
        comment = "$it 음식에 대한 리뷰를 작성해보았습니다.",
        userName = "User$it",
        updateDate = LocalDateTime.now()
    )
}

