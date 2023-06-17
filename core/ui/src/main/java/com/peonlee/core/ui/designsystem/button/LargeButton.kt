package com.peonlee.core.ui.designsystem.button

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.peonlee.core.ui.R
import com.peonlee.core.ui.base.BaseCustomView
import com.peonlee.core.ui.databinding.PeonleeLargeButtonBinding

class LargeButton (
    context: Context,
    attributeSet: AttributeSet
) : BaseCustomView<PeonleeLargeButtonBinding>(
    context = context,
    attributeSet = attributeSet,
    styleable = R.styleable.LargeButton
)
{
    init {
        applyAttributes(attributeSet)
    }

    override fun applyAttributes(attributeSet: AttributeSet) {
        customTypeArray.apply {
            val titleText = getString(R.styleable.LargeButton_android_text)
            val titleTextColor = getColor(
                R.styleable.LargeButton_android_textColor,
                resources.getColor(
                    R.color.bg80,
                    context.theme
                )
            )

            val largeButtonBackground = getResourceId(
                R.styleable.LargeButton_android_background,
                R.drawable.bg_white_radius_10dp
            )

            val largeButtonBackgroundTint = getColor(
                R.styleable.LargeButton_android_backgroundTint,
                resources.getColor(
                    R.color.transparent,
                    context.theme
                )
            )

            val isShowingChevron = getBoolean(
                R.styleable.LargeButton_showChevron,
                false
            )

            applyTextAttributes(
                titleText,
                titleTextColor
            )
            applyBackgroundAttributes(
                largeButtonBackground,
                largeButtonBackgroundTint
            )
            setChevronVisible(isShowingChevron)

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
        binding.layoutLargeButtonBackground.apply {
            setBackgroundResource(background)
            backgroundTintList = ColorStateList.valueOf(backgroundTint)
        }
    }

    fun setChevronVisible(isShowingChevron: Boolean) {
        binding.ivChevronRight.isVisible = isShowingChevron
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        binding.tvTitle.apply {
            if (lineCount == 1) layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
        }
    }

    override var text: String = ""
        set(value) {
            binding.tvTitle.text = value
            field = value
        }

    override fun bindingFactory(): PeonleeLargeButtonBinding {
        return PeonleeLargeButtonBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }
}
