package com.peonlee.core.ui.designsystem.selector

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.peonlee.core.ui.R
import com.peonlee.core.ui.base.BaseCustomView
import com.peonlee.core.ui.databinding.PeonleeSmallSelectorBinding

class SmallSelector(
    context: Context,
    val attributeSet: AttributeSet
) : BaseCustomView<PeonleeSmallSelectorBinding>(
    context = context,
    attributeSet = attributeSet,
    styleable = R.styleable.SmallSelector
) {

    override var text: String = ""
        set(value) {
            binding.tvSelectorTitle.text = value
            field = value
        }

    init {
        applyAttributes(attributeSet)
    }

    override fun applyAttributes(attributeSet: AttributeSet) {
        customTypeArray.apply {
            val selectorTitleText = getString(R.styleable.MediumChip_android_text)
            val selectorTextColor = getColor(
                R.styleable.MediumChip_android_textColor,
                resources.getColor(
                    R.color.bg50,
                    context.theme
                )
            )

            val selectorBackground = getResourceId(
                R.styleable.SmallSelector_android_background,
                R.drawable.bg_white_outline_radius_17dp
            )

            val isShowingIcon = getBoolean(
                R.styleable.SmallSelector_showIcon,
                false
            )

            applyTextAttributes(
                selectorTitleText,
                selectorTextColor
            )

            applyBackground(selectorBackground)

            showIcon(isShowingIcon)

            if (isShowingIcon) {
                val selectorIconBackground = getResourceId(
                    R.styleable.SmallSelector_iconBackground,
                    R.drawable.ic_chevron_bottom
                )

                val selectorIconBackgroundTint = getColor(
                    R.styleable.SmallSelector_iconBackgroundTint,
                    resources.getColor(
                        R.color.bg30,
                        context.theme
                    )
                )
                applyIconAttributes(
                    selectorIconBackground,
                    selectorIconBackgroundTint
                )
            }
        }
    }

    override fun applyTextAttributes(
        titleText: String?,
        titleTextColor: Int
    ) {
        binding.tvSelectorTitle.apply {
            text = titleText
            setTextColor(titleTextColor)
        }
    }

    fun applyBackground(background: Int) {
        binding.layoutSelectorBackground.setBackgroundResource(background)
    }

    private fun applyIconAttributes(
        background: Int,
        backgroundTint: Int
    ) {
        binding.ivSelectorIcon.apply {
            setImageResource(background)
            imageTintList = ColorStateList.valueOf(backgroundTint)
        }
    }

    fun showIcon(isShowingIcon: Boolean) {
        binding.ivSelectorIcon.isVisible = isShowingIcon
    }

    fun setFillColor() {
        val fillColor = ContextCompat.getColor(context, R.color.brand100)
        with(binding) {
            tvSelectorTitle.setTextColor(fillColor)
            ivSelectorIcon.imageTintList = ColorStateList.valueOf(fillColor)
            this@SmallSelector.setBackgroundResource(R.drawable.bg_white_outline_brand100_radius_17dp)
        }
    }

    fun setCancelColor() {
        this@SmallSelector.setBackgroundResource(R.drawable.bg_white_outline_radius_17dp)
        applyAttributes(attributeSet)
    }

    override fun bindingFactory(): PeonleeSmallSelectorBinding {
        return PeonleeSmallSelectorBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }
}
