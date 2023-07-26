package com.peonlee.termsdetail

import android.annotation.SuppressLint
import android.webkit.WebViewClient
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.termsdetail.databinding.ActivityTermsDetailActivityBinding

class TermsDetailActivity : BaseActivity<ActivityTermsDetailActivityBinding>() {
    override fun bindingFactory() = ActivityTermsDetailActivityBinding.inflate(layoutInflater)

    @SuppressLint("SetJavaScriptEnabled")
    override fun initViews() = with(binding) {
        tvDetailTitle.text = intent.getStringExtra("title")
        webviewTerms.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            loadUrl(intent.getStringExtra("url") ?: return)
        }
        ivDetailClose.setOnClickListener { finish() }
    }
}
