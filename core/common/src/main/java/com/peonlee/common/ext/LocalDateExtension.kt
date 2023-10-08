package com.peonlee.common.ext

import java.time.LocalDate

fun LocalDate.format(separator: String): String {
    return listOf(year, monthValue, dayOfMonth).joinToString(separator = separator)
}
