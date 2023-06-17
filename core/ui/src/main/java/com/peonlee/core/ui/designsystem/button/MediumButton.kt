package com.peonlee.core.ui.designsystem.button

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.peonlee.core.ui.R
import com.peonlee.core.ui.base.BaseCustomView
import com.peonlee.core.ui.databinding.PeonleeMediumButtonBinding

class MediumButton constructor(
    context: Context,
    attributeSet: AttributeSet
) : BaseCustomView<PeonleeMediumButtonBinding>(
    context = context,
    attributeSet = attributeSet,
    styleable = R.styleable.MediumButton
) {
    init {
        applyAttributes(attributeSet)
    }

    override fun applyAttributes(attributeSet: AttributeSet) {
        customTypeArray.apply {
            val titleText = getString(R.styleable.MediumButton_android_text)
            val titleTextColor = getColor(
                R.styleable.MediumButton_android_textColor,
                resources.getColor(
                    R.color.bg0,
                    context.theme
                )
            )

            val mediumButtonBackground = getResourceId(
                R.styleable.MediumButton_android_background,
                R.drawable.bg_white_radius_10dp
            )

            val mediumButtonBackgroundTint = getColor(
                R.styleable.MediumButton_android_backgroundTint,
                resources.getColor(
                    R.color.brand100,
                    context.theme
                )
            )

            val isShowingThumbs = getBoolean(
                R.styleable.MediumButton_showThumbs,
                false
            )

            val thumbsBackground = getResourceId(
                R.styleable.MediumButton_thumbsBackground,
                R.drawable.ic_thumbs_up
            )

            val thumbsBackgroundTint = getColor(
                R.styleable.MediumButton_thumbsBackgroundTint,
                resources.getColor(
                    R.color.brand60,
                    context.theme
                )
            )

            applyTextAttributes(
                titleText,
                titleTextColor
            )

            applyImageAttributes(
                isShowingThumbs,
                thumbsBackground,
                thumbsBackgroundTint
            )

            applyBackgroundAttributes(
                mediumButtonBackground,
                mediumButtonBackgroundTint
            )

            recycle()
        }
    }

    override fun applyTextAttributes(
        titleText: String?,
        titleTextColor: Int
    ) {
        binding.tvTitle.apply {
            text = titleText
            setTextColor(titleTextColor)
        }
    }

    override fun applyBackgroundAttributes(
        background: Int,
        backgroundTint: Int
    ) {
        binding.layoutMediumButtonBackground.apply {
            setBackgroundResource(background)
            backgroundTintList = ColorStateList.valueOf(backgroundTint)
        }
    }

    private fun applyImageAttributes(
        isShowingThumbs: Boolean,
        thumbsBackground: Int,
        thumbsBackgroundTint: Int
    ) {
        binding.ivThumbs.apply {
            imageTintList = ColorStateList.valueOf(thumbsBackgroundTint)
            isVisible = isShowingThumbs
            setImageResource(thumbsBackground)
        }
    }

    fun setThumbsVisible(isShowingThumbs: Boolean) {
        binding.ivThumbs.isVisible = isShowingThumbs
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        binding.tvTitle.apply {
            if (lineCount == 1) layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
        }
    }

    override var text: String = binding.tvTitle.text.toString()
        set(value) {
            binding.tvTitle.text = value
            field = value
        }

    override fun bindingFactory(): PeonleeMediumButtonBinding {
        return PeonleeMediumButtonBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }
}
