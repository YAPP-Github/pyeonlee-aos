package com.peonlee.model.type

enum class SortType(
    val uiNameForHome: String,
    val uiNameForExplore: String,
    val sortName: String // 서버와 연동할 order type 이름
) {
    RECENT("신상품", "최신순", "RECENT"),
    POPULAR("인기상품", "인기순", "POPULAR"),
    LIKE("", "평가 많은순", "LIKE"),
    COMMENT("", "리뷰 많은순", "COMMENT");
}
