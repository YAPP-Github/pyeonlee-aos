package com.peonlee.onboading

import android.content.Intent
import androidx.activity.viewModels
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.core.ui.extensions.showToast
import com.peonlee.evaluate.EvaluateFragment
import com.peonlee.main.MainActivity
import com.peonlee.onboading.databinding.ActivityOnboadingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardActivity : BaseActivity<ActivityOnboadingBinding>() {
    private val onboardViewModel: OnboardViewModel by viewModels()

    override fun bindingFactory(): ActivityOnboadingBinding {
        return ActivityOnboadingBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        supportFragmentManager.beginTransaction()
            .add(R.id.layout_onboarding, EvaluateFragment.getInstance())
            .commit()

        binding.tvNext.setOnClickListener {
            if (onboardViewModel.evaluatedProductCount >= EVALUATE_PRODUCT_COUNT) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                showToast(R.string.evaluate_count)
            }
        }
    }

    companion object {
        private const val EVALUATE_PRODUCT_COUNT = 10
    }
}
