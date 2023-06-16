package com.peonlee.core.ui.base

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewbinding.ViewBinding
import com.peonlee.core.ui.R

abstract class BaseCustomView<T : ViewBinding> constructor(
    context: Context,
    attributeSet: AttributeSet,
    styleable: IntArray
) : ConstraintLayout(context, attributeSet) {

    val binding by lazy { bindingFactory() }
    val customTypeArray = context.obtainStyledAttributes(attributeSet, styleable)

    abstract fun bindingFactory(): T
    abstract fun applyAttributes(attributeSet: AttributeSet)
    abstract fun applyTextAttributes(
        titleText: String?,
        titleTextColor: Int = R.color.bg0
    )
    abstract fun applyBackgroundAttributes(background: Int = R.color.bg0)
}
