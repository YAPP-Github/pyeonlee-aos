package com.peonlee.model.type

/**
 * 상품 정렬 type
 * [사용 Activity]
 * - 메인 : 홈
 * - 메인 : 탐색
 */
enum class SortType(
    val sortName: String // 정렬 이름
) {
    LATEST("최신순"),
    MOST_POPULAR("인기순"),
    MOST_EVALUATE("평가순"),
    MOST_REVIEW("리뷰순")
}
