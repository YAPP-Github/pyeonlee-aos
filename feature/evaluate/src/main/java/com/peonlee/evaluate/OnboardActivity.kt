package com.peonlee.evaluate

import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.evaluate.databinding.ActivityOnboadingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardActivity : BaseActivity<ActivityOnboadingBinding>() {
    override fun bindingFactory(): ActivityOnboadingBinding {
        return ActivityOnboadingBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        supportFragmentManager.beginTransaction()
            .add(R.id.layout_onboarding, EvaluateFragment())
            .commit()
    }

    override fun bindViews() {
    }
}
