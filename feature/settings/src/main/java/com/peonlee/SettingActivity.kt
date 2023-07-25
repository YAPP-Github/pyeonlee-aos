package com.peonlee

import android.content.Intent
import androidx.lifecycle.lifecycleScope
import com.peonlee.core.ui.Navigator
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.data.local.LocalDataSource
import com.peonlee.settings.R
import com.peonlee.core.ui.R.string as String
import com.peonlee.settings.databinding.ActivitySettingBinding
import com.peonlee.termsdetail.TermsDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SettingActivity : BaseActivity<ActivitySettingBinding>() {
    @Inject lateinit var dataStore: LocalDataSource
    @Inject lateinit var navigator: Navigator

    override fun bindingFactory() = ActivitySettingBinding.inflate(layoutInflater)

    override fun initViews() = with(binding) {
        val termsList: List<SettingUiModel> = listOf(
            SettingUiModel(getString(R.string.terms_of_use_service), getString(String.terms_url)),
            SettingUiModel(getString(R.string.agree_personal), getString(String.agree_personal_url)),
            SettingUiModel(getString(R.string.personal_policy), getString(String.personal_url)),
            SettingUiModel(getString(R.string.logout), "")
        )

        val settingAdapter = SettingAdapter { settingUiModel ->
            handleEvent(settingUiModel)
        }.apply {
            submitList(termsList)
        }
        rvTermsList.adapter = settingAdapter
        ivSettingClose.setOnClickListener { finish() }
    }

    private fun handleEvent(event: SettingUiModel) {
        when (event.url.isNotEmpty()) {
            true -> {
                val intent = Intent(this, TermsDetailActivity::class.java).apply {
                    putExtra("title", event.termTitle)
                    putExtra("url", event.url)
                }
                startActivity(intent)
            }
            false -> logout()
        }
    }

    private fun logout() {
        lifecycleScope.launch { dataStore.setAccessToken("") }
        navigator.navigateToLogin(this)
    }
}
