package com.peonlee.model

interface ListItem {
    val id: Long
    val viewType: Enum<*>
}

/**
 * 메인-홈
 */
interface MainHomeListItem : ListItem
enum class MainHomeViewType {
    TITLE, // 제목
    DIVIDER, // 구분선
    CONDITIONAL_PRODUCTS, // 조건별 상품 리스트
    RECENT_REVIEW, // 최신 리뷰
    EVENT_BY_STORE,
    EVENT, // 이벤트
    BUTTON
}
