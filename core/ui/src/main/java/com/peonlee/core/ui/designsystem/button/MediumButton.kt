package com.peonlee.core.ui.designsystem.button

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.peonlee.core.ui.R
import com.peonlee.core.ui.databinding.PeonleeMediumButtonBinding
import com.peonlee.core.ui.extensions.gone
import com.peonlee.core.ui.extensions.visible

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
                R.drawable.background_radius_10dp
            )

            val isShowingThumbs = mediumButtonTypedArray.getBoolean(
                R.styleable.MediumButton_showThumbs,
                false
            )

            val thumbsBackground = mediumButtonTypedArray.getResourceId(
                R.styleable.MediumButton_thumbsBackground,
                R.drawable.icons_medium_thumbs_up
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
        text: String?,
        textColor: Int
    ) {
        binding.tvTitle.apply {
            this.text = text
            this.setTextColor(textColor)
        }
    }

    private fun applyBackgroundAttributes(mediumButtonBackground: Int) {
        binding.layoutMediumBtnBackground.apply {
            setBackgroundResource(mediumButtonBackground)
            backgroundTintList = ColorStateList.valueOf(R.styleable.MediumButton_android_backgroundTint)
        }
    }

    private fun applyImageAttributes(
        isShowingThumbs: Boolean,
        thumbsBackground: Int,
        thumbsBackgroundTint: Int
    ) {
        if (isShowingThumbs) binding.ivThumbs.visible() else binding.ivThumbs.gone()

        binding.ivThumbs.apply {
            setImageResource(thumbsBackground)
            imageTintList = ColorStateList.valueOf(thumbsBackgroundTint)
        }
    }
}
