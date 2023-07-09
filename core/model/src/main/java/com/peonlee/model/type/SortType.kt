package com.peonlee.model.type

enum class SortType(
    val uiNameForHome: String,
    val sortName: String // 서버와 연동할 order type 이름
) {
    RECENT("신상품", "RECENT"),
    POPULAR("인기상품", "POPULAR"),
    LIKE("", "LIKE"),
    COMMENT("", "COMMENT")
}
