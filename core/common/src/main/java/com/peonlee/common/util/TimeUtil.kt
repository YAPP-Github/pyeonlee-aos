package com.peonlee.common.util

import android.content.Context
import com.peonlee.common.R
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

        val year = duration / YEAR
        val month = duration / MONTH
        val day = duration / DAY
        val hour = duration / HOUR
        val minute = duration / MINUTE
        val second = duration / SECOND

        return with(context) {
            when {
                year > 0 -> getString(R.string.duration_year, year)
                month > 0 -> getString(R.string.duration_month, month)
                day > 0 -> getString(R.string.duration_day, day)
                hour > 0 -> getString(R.string.duration_hour, hour)
                minute > 0 -> getString(R.string.duration_minute, minute)
                second > 0 -> getString(R.string.duration_second, second)
                else -> getString(R.string.duration_soon)
            }
        }
    }
}
