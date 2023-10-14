package com.peonlee.common.ext

import java.time.LocalDate

fun LocalDate.format(separator: String): String {
    val month = monthValue.padStart()
    val day = dayOfMonth.padStart()
    return listOf(year, month, day).joinToString(separator = separator)
}
