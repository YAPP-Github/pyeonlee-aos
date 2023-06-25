package com.peonlee.common.util

import android.content.Context
import java.time.Duration
import java.time.LocalDateTime

/**
 * time 과 관련된 util
 */
object TimeUtil {
    private const val SECOND = 1000L
    private const val MINUTE = SECOND * 60
    private const val HOUR = MINUTE * 60
    private const val DAY = HOUR * 24
    private const val MONTH = DAY * 30
    private const val YEAR = MONTH * 12

    /**
     * 현재 시간과 time 까지의 기간을 반환
     * @param context 해당 메서드 를 호출 하는 context
     * @param time from time
     */
    fun getDuration(context: Context, time: LocalDateTime): String {
        val duration = Duration.between(time, LocalDateTime.now()).toMillis()
        return when {
            duration / YEAR > 0 -> "${duration / YEAR}년"
            duration / MONTH > 0 -> "${duration / MONTH}개월"
            duration / DAY > 0 -> "${duration / DAY}일"
            duration / HOUR > 0 -> "${duration / HOUR}시간"
            duration / MINUTE > 0 -> "${duration / MINUTE}분"
            duration / SECOND > 0 -> "${duration / SECOND}초"
            else -> "방금"
        } + "전"
    }
}
