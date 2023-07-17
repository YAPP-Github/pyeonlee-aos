package com.peonlee.core.ui.extensions

import android.view.View
import android.widget.EditText
import com.peonlee.core.ui.util.keyboard.KeyboardUtil

/**
 * EditText 에 Focus 시,
 * 키보드 도 함께 생성 되도록 처리
 */
fun EditText.focus() {
    requestFocus() // focus 요청
    KeyboardUtil.show(findFocus()) // 키보드 생성
}

fun EditText.hideKeyboard() {
    KeyboardUtil.hide(this)
}

fun EditText.trim() : String {
    return this.text.trim().toString()
}
