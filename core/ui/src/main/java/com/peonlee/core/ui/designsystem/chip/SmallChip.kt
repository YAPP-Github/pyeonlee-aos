package com.peonlee.core.ui.designsystem.chip

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import com.peonlee.core.ui.R
import com.peonlee.core.ui.base.BaseCustomView
import com.peonlee.core.ui.databinding.PeonleeSmallChipBinding

class SmallChip(
    context: Context,
    attributeSet: AttributeSet
) : BaseCustomView<PeonleeSmallChipBinding>(
    context = context,
    attributeSet = attributeSet,
    styleable = R.styleable.SmallChip
) {

    init {
        applyAttributes(attributeSet)
    }

    override fun applyAttributes(attributeSet: AttributeSet) {
        customTypeArray.apply {
            val chipTitleText = getString(R.styleable.SmallChip_android_text)
            val chipTitleTextColor = getColor(
                R.styleable.MediumButton_android_textColor,
                resources.getColor(
                    R.color.system_y100,
                    context.theme
                )
            )
            val chipBackgroundTint = getColor(
                R.styleable.SmallChip_android_backgroundTint,
                resources.getColor(
                    R.color.system_r50,
                    context.theme
                )
            )
            /**
             * TODO applyBackgroundAttributes Method 수정 필요
             * background, backgroundTint 둘 중에 하나만 설정할 수 있는 코드
             */
            binding.layoutSmallChipBackground.apply {
                backgroundTintList = ColorStateList.valueOf(chipBackgroundTint)
            }

            applyTextAttributes(
                chipTitleText,
                chipTitleTextColor
            )
            recycle()
        }
    }

    override fun applyTextAttributes(
        titleText: String?,
        titleTextColor: Int
    ) {
        binding.tvSmallChipTitle.apply {
            text = titleText
            setTextColor(titleTextColor)
        }
    }

    override fun bindingFactory(): PeonleeSmallChipBinding {
        return PeonleeSmallChipBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }
}
