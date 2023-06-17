package com.peonlee.core.ui.extensions

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView.ViewHolder

fun ViewHolder.getString(
    @StringRes id: Int
): String {
    return itemView.context.getString(id)
}

fun ViewHolder.getStringWithArgs(
    @StringRes id: Int,
    vararg formatArgs: Any
): String {
    return itemView.context.getString(id, *formatArgs)
}

fun ViewHolder.getColor(
    @ColorRes id: Int
): Int {
    return itemView.context.getColor(id)
}
