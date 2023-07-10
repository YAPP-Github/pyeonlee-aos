package com.peonlee.model.type

import androidx.annotation.StringRes
import com.peonlee.model.R

/**
 * 상품 정렬 type
 * [사용 Activity]
 * - 메인 : 홈
 * - 메인 : 탐색
 */
enum class SortType(
    @StringRes
    val sortName: Int // 정렬 이름
) {
    LATEST(R.string.sort_latest),
    MOST_POPULAR(R.string.sort_most_popular),
    MOST_EVALUATE(R.string.sort_most_evaluate),
    MOST_REVIEW(R.string.sort_most_review)
}
