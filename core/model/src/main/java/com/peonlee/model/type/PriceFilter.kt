package com.peonlee.model.type

import android.content.Context
import com.peonlee.common.ext.toFormattedMoney
import com.peonlee.model.R

enum class PriceFilter(
    val minPrice: Int,
    val maxPrice: Int?
) {
    ONE_POINT_FIVE(0, 1_500),
    FIVE_ZERO(1_500, 5_000),
    TEN_ZERO(5_000, 10_000),
    OVER_TEN(10_000, null)
}

fun PriceFilter.toRangeString(context: Context) =
    if (maxPrice != null) {
        context.getString(
            R.string.full_price_range,
            minPrice.toFormattedMoney(),
            maxPrice.toFormattedMoney()
        )
    } else {
        context.getString(
            R.string.half_price_range,
            minPrice.toFormattedMoney()
        )
    }
