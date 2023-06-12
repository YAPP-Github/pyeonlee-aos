package com.peonlee.core.ui.extensions

import android.view.View

fun View.gone() {
    this.visibility = View.GONE
}

fun View.inVisible() {
    this.visibility = View.INVISIBLE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}
