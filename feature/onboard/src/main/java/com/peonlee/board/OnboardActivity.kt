package com.peonlee.board

import android.os.Bundle
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.evaluate.EvaluateFragment
import com.peonleeonboard.R
import com.peonleeonboard.databinding.ActivityOnboadingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardActivity : BaseActivity<ActivityOnboadingBinding>() {
    override fun bindingFactory(): ActivityOnboadingBinding {
        return ActivityOnboadingBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        val evaluateFragment = EvaluateFragment().apply {
            arguments = Bundle().apply {
                putBoolean("onBoarding", true)
            }
        }
        supportFragmentManager.beginTransaction().add(
            R.id.layout_onboarding,
            evaluateFragment
        ).commit()
    }

    override fun bindViews() {
    }
}
