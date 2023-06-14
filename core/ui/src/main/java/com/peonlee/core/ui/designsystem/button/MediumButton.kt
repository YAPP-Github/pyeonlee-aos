package com.peonlee.core.ui.designsystem.button

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.peonlee.core.ui.R
import com.peonlee.core.ui.databinding.PeonleeMediumButtonBinding

class MediumButton constructor(
    context: Context,
    attributeSet: AttributeSet
) : ConstraintLayout(context, attributeSet) {

    private val binding: PeonleeMediumButtonBinding =
        PeonleeMediumButtonBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )

    init {
        applyAttributes(attributeSet)
    }

    private fun applyAttributes(attributeSet: AttributeSet) {
        val mediumButtonTypedArray = context.obtainStyledAttributes(attributeSet, R.styleable.MediumButton)

        mediumButtonTypedArray.apply {
            val titleText = mediumButtonTypedArray.getString(R.styleable.MediumButton_android_text)
            val titleTextColor = mediumButtonTypedArray.getColor(
                R.styleable.MediumButton_android_textColor,
                resources.getColor(
                    R.color.bg80,
                    context.theme
                )
            )

            val mediumButtonBackground = mediumButtonTypedArray.getResourceId(
                R.styleable.MediumButton_android_background,
                R.drawable.bg_radius_10dp
            )

            val isShowingThumbs = mediumButtonTypedArray.getBoolean(
                R.styleable.MediumButton_showThumbs,
                false
            )

            val thumbsBackground = mediumButtonTypedArray.getResourceId(
                R.styleable.MediumButton_thumbsBackground,
                R.drawable.ic_thumbs_up
            )

            val thumbsBackgroundTint = mediumButtonTypedArray.getColor(
                R.styleable.MediumButton_thumbsBackgroundTint,
                resources.getColor(
                    R.color.bg80,
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

            applyBackgroundAttributes(mediumButtonBackground)

            recycle()
        }
    }

    private fun applyTextAttributes(
        titleText: String?,
        titleTextColor: Int
    ) {
        binding.tvTitle.apply {
            text = titleText
            setTextColor(titleTextColor)
        }
    }

    private fun applyBackgroundAttributes(mediumButtonBackground: Int) {
        binding.layoutMediumButtonBackground.apply {
            setBackgroundResource(mediumButtonBackground)
            backgroundTintList = ColorStateList.valueOf(R.styleable.MediumButton_android_backgroundTint)
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

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        binding.tvTitle.apply {
            if (lineCount == 1) layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
        }
    }
}
