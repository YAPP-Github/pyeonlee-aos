package com.peonlee.data.model.home

import com.peonlee.data.model.Content
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 홈 화면에 필요한 모든 데이터 요청 Response
 */
@Serializable
data class HomeInfoResponse(
    @SerialName("newProductList")
    val newProductList: List<Content>, // 신상품
    @SerialName("popularProductList")
    val popularProductList: List<Content>, // 인기 상품
    @SerialName("promotionProductMap")
    val promotionProductMap: Map<String, List<Content>>, // 편의점 별 행사 상품
    @SerialName("recentProductCommentList")
    val recentProductCommentList: List<HomeReviewResponse>
)
