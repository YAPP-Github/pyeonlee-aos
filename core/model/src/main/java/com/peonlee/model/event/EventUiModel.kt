package com.peonlee.model.event

import com.peonlee.model.MainHomeListItem
import com.peonlee.model.MainHomeViewType
import com.peonlee.model.type.StoreType
import java.time.LocalDate

/**
 * 홈 화면에 필요한 이벤트 탭
 */
data class EventUiModel(
    override val id: Long,
    override val viewType: MainHomeViewType = MainHomeViewType.EVENT,
    val title: String, // 제목
    val imageUrl: String, // 이벤트 이미지 경로
    val store: StoreType, // 편의점
    val startedDate: LocalDate, // 시작 날짜
    val endedDate: LocalDate // 끝나는 날짜
) : MainHomeListItem

val EVENT_DUMMY = (0..2).map {
    EventUiModel(
        id = it.toLong() * 10000000,
        title = "이벤트 $it",
        imageUrl = "https://www.7-eleven.co.kr/upload/event//thumbnail/20230926132326644r210.jpg",
        store = StoreType.SEVEN,
        startedDate = LocalDate.now().minusDays(10),
        endedDate = LocalDate.now().plusDays(it.toLong())
    )
}
