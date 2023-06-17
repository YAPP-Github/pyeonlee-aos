package com.peonlee.core.ui.util.keyboard

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * keyboard manager util
 */
object KeyboardUtil {
    /**
     * keyboard open
     */
    fun show(view: View?) {
        view ?: return
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, 0)
    }

    /**
     * keyboard hide
     */
    fun hide(view: View?) {
        view ?: return
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.applicationWindowToken, 0)
    }
}
