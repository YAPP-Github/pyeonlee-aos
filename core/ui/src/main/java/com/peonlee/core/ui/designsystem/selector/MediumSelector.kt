package com.peonlee.core.ui.designsystem.selector

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.peonlee.core.ui.R
import com.peonlee.core.ui.base.BaseCustomView
import com.peonlee.core.ui.databinding.PeonleeMediumSelectorBinding

class MediumSelector(
    context: Context,
    val attributeSet: AttributeSet
) : BaseCustomView<PeonleeMediumSelectorBinding>(
    context = context,
    attributeSet = attributeSet,
    styleable = R.styleable.MediumSelector
) {

    override var text: String = ""
        set(value) {
            binding.tvMediumSelectorTitle.text = value
            field = value
        }

    init {
        applyAttributes(attributeSet)
    }

    override fun applyAttributes(attributeSet: AttributeSet) {
        customTypeArray.apply {
            val selectorTitleText = getString(R.styleable.MediumSelector_android_text)

            val selectorTextColor = getColor(
                R.styleable.MediumSelector_android_textColor,
                resources.getColor(
                    R.color.bg80,
                    context.theme
                )
            )

            val selectorBackground = getResourceId(
                R.styleable.MediumSelector_android_background,
                R.drawable.bg_white_outline_radius_17dp
            )

            val selectorIcon = getResourceId(
                R.styleable.MediumSelector_iconBackground2,
                R.drawable.ic_store_cu
            )

            applyTextAttributes(
                selectorTitleText,
                selectorTextColor
            )

            setIcon(selectorIcon)
            applyBackground(selectorBackground)
        }
    }

    override fun applyTextAttributes(
        titleText: String?,
        titleTextColor: Int
    ) {
        binding.tvMediumSelectorTitle.apply {
            text = titleText
            setTextColor(titleTextColor)
        }
    }

    override fun setIcon(icon: Int) {
        binding.ivMediumSelectorIcon.setImageResource(icon)
    }

    fun applyBackground(background: Int) {
        binding.layoutMediumSelectorBackground.setBackgroundResource(background)
    }

    fun setCancelColor() {
        this@MediumSelector.setBackgroundResource(R.drawable.bg_white_outline_radius_17dp)
        applyAttributes(attributeSet)
    }

    override fun bindingFactory(): PeonleeMediumSelectorBinding {
        return PeonleeMediumSelectorBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }
}
