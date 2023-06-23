package com.peonlee.common.util

import java.lang.StringBuilder
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

    fun getDuration(time: LocalDateTime): String {
        var duration = Duration.between(time, LocalDateTime.now()).toMillis()
        val builder = StringBuilder()

        val year = duration / YEAR
        if (year > 0) {
            builder.append("${year}년")
            duration -= YEAR * year
        }
        val month = duration / MONTH
        if (month > 0) {
            builder.append("${month}개월")
            duration -= MONTH * month
        }
        val day = duration / DAY
        if (day > 0) {
            builder.append("${day}일")
            duration -= DAY * day
        }
        val hour = duration / HOUR
        if (hour > 0) {
            builder.append("${hour}시간")
            duration -= HOUR * hour
        }
        val minute = duration / MINUTE
        if (minute > 0) {
            builder.append("${minute}분")
            duration -= MINUTE * minute
        }
        val second = duration / SECOND
        if (second > 0) {
            builder.append("${second}초")
        }

        return (if (builder.isEmpty()) "방금" else builder.toString()) + " 전"
    }
}
