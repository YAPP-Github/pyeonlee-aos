package com.peonlee.common.ext

import android.content.Context
import java.text.DecimalFormat
import kotlin.math.roundToInt

fun Int.dpToPx(context: Context): Int {
    val density = context.resources.displayMetrics.density
    return (this * density).roundToInt()
}

/**
 * 숫자를 000,000 형식의 String 으로 변환
 */
private val moneyFormat = DecimalFormat("#,###")
fun Int.toFormattedMoney(): String {
    return "${moneyFormat.format(this)}원"
}

fun Int.padStart(zero: Int = 2) = toString().padStart(zero, '0')
