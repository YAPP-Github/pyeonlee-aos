package com.peonlee.feature.terms

import android.app.Activity
import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.terms.databinding.ActivityTermsBinding
import com.peonlee.core.ui.R.color as Color
import com.peonlee.core.ui.R.drawable as Drawable

class TermsActivity : BaseActivity<ActivityTermsBinding>() {
    override fun bindingFactory(): ActivityTermsBinding = ActivityTermsBinding.inflate(layoutInflater)

    override fun initViews() = with(binding) {
        ivTermsClose.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }

        checkboxAllTerms.apply {
            setOnClickListener {
                checkboxService.isChecked = isChecked
                checkboxPersonal.isChecked = isChecked
                updateBackground(isChecked)
                updateNextButton()
            }
        }

        checkboxService.setOnClickListener {
            checkboxAllTerms.isChecked = false
            updateBackground(false)
            updateNextButton()
        }

        checkboxPersonal.setOnClickListener {
            checkboxAllTerms.isChecked = false
            updateBackground(false)
            updateNextButton()
        }

        tvStart.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    private fun updateBackground(isUpdate: Boolean) {
        when (isUpdate) {
            true -> binding.layoutTerms.setBackgroundResource(Drawable.bg_brand30_outline_brand60_radius_10dp)
            false -> binding.layoutTerms.setBackgroundResource(Drawable.bg_white_outline_radius_10dp)
        }
    }

    private fun updateNextButton() {
        val isActive = binding.checkboxService.isChecked && binding.checkboxPersonal.isChecked
        when (isActive) {
            true -> updateState(true)
            false -> updateState(false)
        }
    }

    private fun updateState(isActive: Boolean) {
        binding.tvStart.apply {
            isEnabled = isActive
            backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    this@TermsActivity,
                    if (isActive) Color.brand100 else Color.brand50
                )
            )
        }
    }
}
