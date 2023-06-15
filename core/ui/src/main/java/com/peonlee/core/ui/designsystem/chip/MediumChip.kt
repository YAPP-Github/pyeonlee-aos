package com.peonlee.core.ui.designsystem.chip

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import com.peonlee.core.ui.R
import com.peonlee.core.ui.base.BaseCustomView
import com.peonlee.core.ui.databinding.PeonleeMediumChipBinding

class MediumChip(
    context: Context,
    attributeSet: AttributeSet
) : BaseCustomView<PeonleeMediumChipBinding>(
    context = context,
    attributeSet = attributeSet,
    styleable = R.styleable.MediumChip
) {

    init {
        applyAttributes(attributeSet)
    }

    override fun bindingFactory(): PeonleeMediumChipBinding {
        return PeonleeMediumChipBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }

    override fun applyAttributes(attributeSet: AttributeSet) {
        customTypeArray.apply {
            val chipTitleText = getString(R.styleable.MediumChip_android_text)
            val chipTitleTextColor = getColor(
                R.styleable.MediumChip_android_textColor,
                resources.getColor(
                    R.color.bg80,
                    context.theme
                )
            )

            val chipBackground = getColor(
                R.styleable.MediumChip_android_backgroundTint,
                resources.getColor(
                    R.color.bg80,
                    context.theme
                )
            )

            val chipThumbsBackground = getResourceId(
                R.styleable.MediumChip_chipThumbsBackground,
                R.drawable.ic_thumbs_up
            )

            val chipThumbsBackgroundTint = getColor(
                R.styleable.MediumChip_chipThumbsBackgroundTint,
                resources.getColor(
                    R.color.bg80,
                    context.theme
                )
            )

            applyBackgroundAttributes(chipBackground)

            applyTextAttributes(
                titleText = chipTitleText,
                titleTextColor = chipTitleTextColor
            )

            applyThumbsAttributes(
                chipThumbsBackground = chipThumbsBackground,
                chipThumbsBackgroundTint = chipThumbsBackgroundTint
            )

            recycle()
        }
    }

    override fun applyTextAttributes(
        titleText: String?,
        titleTextColor: Int
    ) {
        binding.tvMediumChipTitle.apply {
            text = titleText
            setTextColor(titleTextColor)
        }
    }

    override fun applyBackgroundAttributes(background: Int) {
        binding.layoutMediumChipBackground.apply {
            backgroundTintList = ColorStateList.valueOf(background)
        }
    }

    private fun applyThumbsAttributes(
        chipThumbsBackground: Int,
        chipThumbsBackgroundTint: Int
    ) {
        binding.ivMediumChipThumbs.apply {
            imageTintList = ColorStateList.valueOf(chipThumbsBackgroundTint)
            setImageResource(chipThumbsBackground)
        }
    }
}
