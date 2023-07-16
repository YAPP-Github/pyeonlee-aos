package com.peonlee.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Content(
    val brandName: String, // 브랜드 이름
    val imageUrl: String, // 이미지 URL
    val isPbProduct: Boolean, // PB 상품 여부
    val isPromotion: Boolean, // 행사 여부
    val price: Int, // 가격
    val productCategoryType: String, // 카테고리 종류
    val productId: Int, // 상품 id
    val productName: String, // 상품 이름
    val score: Score // 점수 정보
)
