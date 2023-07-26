package com.peonlee

import android.content.res.ColorStateList
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.peonlee.core.ui.Navigator
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.core.ui.extensions.showToast
import com.peonlee.withdrawal.R
import com.peonlee.withdrawal.databinding.ActivityWithdrawalBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.peonlee.core.ui.R.color as Color
import com.peonlee.withdrawal.R.string as String

@AndroidEntryPoint
class WithdrawalActivity : BaseActivity<ActivityWithdrawalBinding>() {
    @Inject lateinit var navigator: Navigator
    private val withdrawalViewModel: WithdrawalViewModel by viewModels()

    override fun bindingFactory() = ActivityWithdrawalBinding.inflate(layoutInflater)

    override fun initViews() = with(binding) {
        observeWithdrawal()
        layoutReview.tvEvaluateCount.text = getString(String.count, intent.getIntExtra("evaluateCount", 0))
        layoutReview.tvReviewCount.text = getString(String.count, intent.getIntExtra("reviewCount", 0))
        ivWithdrawalClose.setOnClickListener { finish() }
        btnUse.setOnClickListener { finish() }
        btnUnUse.setOnClickListener { withdrawalViewModel.deleteUser(intent.getIntExtra("memberId", -1)) }
        checkboxAgree.setOnClickListener { setButtonState() }
    }

    private fun observeWithdrawal() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                withdrawalViewModel.withdrawalStateFlow.collect { withdrawalUiState ->
                    when (withdrawalUiState) {
                        is WithdrawalUiState.Init -> Unit
                        is WithdrawalUiState.Success -> startWithdrawal()
                        is WithdrawalUiState.Fail -> showToast(R.string.fail_withdrawal)
                    }
                }
            }
        }
    }

    private fun startWithdrawal() {
        withdrawalViewModel.setAccessToken()
        navigator.navigateToLogin(this)
    }

    private fun setButtonState() {
        with(binding) {
            btnUnUse.isEnabled = checkboxAgree.isChecked
            when (checkboxAgree.isChecked) {
                true -> btnUnUse.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this@WithdrawalActivity, Color.brand100))
                false -> btnUnUse.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this@WithdrawalActivity, Color.brand50))
            }
        }
    }
}
