package com.peonlee.core.ui.designsystem.chip

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
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

    override var text: String = ""
        set(value) {
            binding.tvMediumChipTitle.text = value
            field = value
        }

    init {
        applyAttributes(attributeSet)
    }

    override fun applyAttributes(attributeSet: AttributeSet) {
        customTypeArray.apply {
            val chipTitleText = getString(R.styleable.MediumChip_android_text)
            val chipTitleTextColor = getColor(
                R.styleable.MediumChip_android_textColor,
                resources.getColor(
                    R.color.brand100,
                    context.theme
                )
            )

            val chipBackground = getResourceId(
                R.styleable.MediumChip_android_background,
                R.drawable.bg_white_radius_20dp
            )

            val chipBackgroundTint = getColor(
                R.styleable.MediumChip_android_backgroundTint,
                resources.getColor(
                    R.color.brand40,
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
                    R.color.brand100,
                    context.theme
                )
            )

            applyBackgroundAttributes(
                chipBackground,
                chipBackgroundTint
            )

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

    override fun applyBackgroundAttributes(
        background: Int,
        backgroundTint: Int
    ) {
        binding.layoutMediumChipBackground.apply {
            setBackgroundResource(background)
            backgroundTintList = ColorStateList.valueOf(backgroundTint)
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

    override fun setIcon(icon: Int) {
        binding.ivMediumChipThumbs.setImageResource(icon)
    }

    override fun setIconTint(color: Int) {
        val fillColor = ContextCompat.getColor(context, color)
        binding.ivMediumChipThumbs.imageTintList = ColorStateList.valueOf(fillColor)
    }

    override fun setBackgroundTint(color: Int) {
        val fillColor = ContextCompat.getColor(context, color)
        binding.layoutMediumChipBackground.backgroundTintList = ColorStateList.valueOf(fillColor)
    }

    override fun bindingFactory(): PeonleeMediumChipBinding {
        return PeonleeMediumChipBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }
}
