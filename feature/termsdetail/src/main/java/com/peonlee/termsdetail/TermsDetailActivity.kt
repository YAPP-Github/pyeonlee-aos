package com.peonlee.termsdetail

import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.termsdetail.databinding.ActivityTermsDetailActivityBinding

class TermsDetailActivity : BaseActivity<ActivityTermsDetailActivityBinding>() {
    override fun bindingFactory() = ActivityTermsDetailActivityBinding.inflate(layoutInflater)

    override fun initViews() = with(binding) {
        tvDetailTitle.text = intent.getStringExtra("title")
        webviewTerms.loadUrl(intent.getStringExtra("url") ?: return)
        ivDetailClose.setOnClickListener { finish() }
    }
}
