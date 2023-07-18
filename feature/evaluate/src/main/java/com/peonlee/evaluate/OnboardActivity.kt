package com.peonlee.evaluate

import android.os.Bundle
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.evaluate.databinding.ActivityOnboadingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardActivity : BaseActivity<ActivityOnboadingBinding>() {
    override fun bindingFactory(): ActivityOnboadingBinding {
        return ActivityOnboadingBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        val bundle = Bundle()
        bundle.putBoolean("onBoarding", true)

        supportFragmentManager.beginTransaction()
            .add(R.id.layout_onboarding, EvaluateFragment().apply {
                arguments = bundle
            }).commit()
    }

    override fun bindViews() {
    }
}
