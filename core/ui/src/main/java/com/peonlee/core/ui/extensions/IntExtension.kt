package com.peonlee.core.ui.extensions

import android.content.Context
import java.text.DecimalFormat
import kotlin.math.roundToInt

fun Int.dpToPx(context: Context): Int {
    val density = context.resources.displayMetrics.density
    return (this * density).roundToInt()
}

private val moneyFormat = DecimalFormat("#,###")
fun Int.toFormattedMoney(): String {
    return "${moneyFormat.format(this)}원"
}
