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

    fun applyBackground(background: Int) {
        binding.layoutMediumSelectorBackground.setBackgroundResource(background)
    }

    override fun setIcon(icon: Int) {
        binding.ivMediumSelectorIcon.setImageResource(icon)
    }

    // TODO : 필요한지 확인 필요 없다면 제거해야해.
    fun setFillStoreBackground(type: Store) {
        when (type) {
            Store.CU -> binding.layoutMediumSelectorBackground.setBackgroundResource(R.drawable.bg_store_outline_cu_radius_17dp)
            Store.GS25 -> binding.layoutMediumSelectorBackground.setBackgroundResource(R.drawable.bg_store_outline_gs25_radius_17dp)
            Store.SEVENELEVEN -> binding.layoutMediumSelectorBackground.setBackgroundResource(R.drawable.bg_store_outline_seveneleven_radius_17dp)
        }
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

enum class Store {
    CU, GS25, SEVENELEVEN
}
