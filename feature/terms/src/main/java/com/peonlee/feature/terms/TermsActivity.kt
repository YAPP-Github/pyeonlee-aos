package com.peonlee.feature.terms

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.terms.R
import com.peonlee.terms.databinding.ActivityTermsBinding
import com.peonlee.termsdetail.TermsDetailActivity
import com.peonlee.core.ui.R.color as Color
import com.peonlee.core.ui.R.drawable as Drawable
import com.peonlee.core.ui.R.string as CoreString

class TermsActivity : BaseActivity<ActivityTermsBinding>() {
    override fun bindingFactory(): ActivityTermsBinding = ActivityTermsBinding.inflate(layoutInflater)

    override fun initViews() = with(binding) {
        ivTermsClose.setOnClickListener { finish() }

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

        ivAgreeService.setOnClickListener {
            moveTermsDetailScreen(getString(R.string.terms_service), getString(CoreString.terms_url))
        }

        ivAgreePersonal.setOnClickListener {
            moveTermsDetailScreen(getString(R.string.agree_personal), getString(CoreString.agree_personal_url))
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
        updateState(isActive)
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

    private fun moveTermsDetailScreen(title: String, url: String) {
        val intent = Intent(this, TermsDetailActivity::class.java).apply {
            putExtra("title", title)
            putExtra("url", url)
        }
        startActivity(intent)
    }
}
