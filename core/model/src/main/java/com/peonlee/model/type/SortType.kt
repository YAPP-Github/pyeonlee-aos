package com.peonlee.model.type

enum class SortType(
    val sortName: String // 서버와 연동할 order type 이름
) {
    RECENT("RECENT"),
    POPULAR("POPULAR"),
    LIKE("LIKE"),
    COMMENT("COMMENT")
}
