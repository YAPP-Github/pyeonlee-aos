package com.peonlee.core.ui.designsystem.selector

import android.content.Context
import android.content.res.ColorStateList
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

            applyIconAttributes(selectorIcon)

            applyBackgroundAttributes(
                selectorBackground,
                0
            )
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

    override fun applyBackgroundAttributes(
        background: Int,
        backgroundTint: Int
    ) {
        binding.layoutMediumSelectorBackground.apply {
            setBackgroundResource(background)
            backgroundTintList = ColorStateList.valueOf(backgroundTint)
        }
    }

    fun applyIconAttributes(
        background: Int,
    ) {
        binding.ivMediumSelectorIcon.apply {
            setImageResource(background)
        }
    }

    fun setFillColor(type: String) {
        when(type) {
            "CU" -> this@MediumSelector.setBackgroundResource(R.drawable.bg_store_outline_cu_radius_17dp)
            "GS25" -> this@MediumSelector.setBackgroundResource(R.drawable.bg_store_outline_gs25_radius_17dp)
            "SEVENELEVEN" -> this@MediumSelector.setBackgroundResource(R.drawable.bg_store_outline_seveneleven_radius_17dp)
        }
    }

    fun setCancelColor() {
        this@MediumSelector.setBackgroundResource(R.drawable.bg_white_outline_radius_17dp)
        applyAttributes(attributeSet)
    }

    override var text: String = ""
        set(value) {
            binding.tvMediumSelectorTitle.text = value
            field = value
        }

    override fun bindingFactory(): PeonleeMediumSelectorBinding {
        return PeonleeMediumSelectorBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }
}
