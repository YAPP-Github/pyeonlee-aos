package com.peonlee.board

import android.content.Intent
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.core.ui.base.Navigatable
import com.peonlee.evaluate.EvaluateFragment
import com.peonlee.main.MainActivity
import com.peonleeonboard.R
import com.peonleeonboard.databinding.ActivityOnboadingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardActivity : BaseActivity<ActivityOnboadingBinding>(), Navigatable {
    override fun bindingFactory(): ActivityOnboadingBinding {
        return ActivityOnboadingBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        supportFragmentManager.beginTransaction()
            .add(R.id.layout_onboarding, EvaluateFragment.getInstance())
            .commit()
    }

    override fun bindViews() {
    }

    override fun moveToNextPage() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
