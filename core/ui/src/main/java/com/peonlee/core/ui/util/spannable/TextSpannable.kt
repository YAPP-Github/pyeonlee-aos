package com.peonlee.core.ui.util.spannable

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.peonlee.core.ui.R

fun setTextSpannable(
    start: Int = 0,
    end: Int,
    contents: String,
    context: Context,
    @ColorRes textColor: Int = R.color.brand100
): SpannableString {
    val spannableText = SpannableString(contents).apply {
        setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    context,
                    textColor
                )
            ),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    return spannableText
}
