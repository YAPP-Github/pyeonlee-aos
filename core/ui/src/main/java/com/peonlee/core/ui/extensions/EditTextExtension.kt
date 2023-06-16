package com.peonlee.core.ui.extensions

import android.widget.EditText
import com.peonlee.core.ui.util.KeyboardUtil

fun EditText.focus() {
    requestFocus()
    KeyboardUtil.show(findFocus())
}
