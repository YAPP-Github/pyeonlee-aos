package com.peonlee.core.ui.extensions

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

/**
 * Toast with message String
 */
fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

/**
 * Toast with message resource id
 */
fun Context.showToast(@StringRes resId: Int) {
    Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show()
}
