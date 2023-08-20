package com.peonlee

import android.content.Intent
import androidx.lifecycle.lifecycleScope
import com.peonlee.core.ui.Navigator
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.data.local.LocalDataSource
import com.peonlee.settings.R
import com.peonlee.settings.databinding.ActivitySettingBinding
import com.peonlee.termsdetail.TermsDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.peonlee.core.ui.R.string as String

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
        tvWithdrawal.setOnClickListener { moveWithdrawalScreen() }
    }

    private fun handleEvent(event: SettingUiModel) {
        when (event.url.isNotEmpty()) {
            true -> {
                val intent = Intent(this, TermsDetailActivity::class.java).apply {
                    putExtra(EXTRA_TITLE, event.termTitle)
                    putExtra(EXTRA_URL, event.url)
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

    private fun moveWithdrawalScreen() {
        val intent = Intent(this, WithdrawalActivity::class.java).apply {
            putExtra(EXTRA_REVIEW_COUNT, intent.getIntExtra("reviewCount", 0))
            putExtra(EXTRA_EVALUATE_COUNT, intent.getIntExtra("evaluateCount", 0))
            putExtra(EXTRA_MEMBER_ID, intent.getIntExtra("memberId", -1))
        }
        startActivity(intent)
    }

    companion object {
        private const val EXTRA_TITLE = "title"
        private const val EXTRA_URL = "url"

        private const val EXTRA_REVIEW_COUNT = "reviewCount"
        private const val EXTRA_EVALUATE_COUNT = "evaluateCount"
        private const val EXTRA_MEMBER_ID = "memberId"
    }
}
