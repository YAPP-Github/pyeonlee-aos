package com.peonlee

import android.content.Intent
import android.content.res.ColorStateList
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.core.ui.extensions.showToast
import com.peonlee.login.LoginActivity
import com.peonlee.withdrawal.R
import com.peonlee.core.ui.R.color as Color
import com.peonlee.withdrawal.databinding.ActivityWithdrawalBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WithdrawalActivity : BaseActivity<ActivityWithdrawalBinding>() {
    private val withdrawalViewModel: WithdrawalViewModel by viewModels()

    override fun bindingFactory() = ActivityWithdrawalBinding.inflate(layoutInflater)

    override fun initViews() = with(binding) {
        observeWithdrawal()

        // TODO 프래그먼트에서 전송된 값 매핑, memberId 매핑
        layoutReview.tvEvaluateCount.text = "0개"
        layoutReview.tvReviewCount.text = "0개"
        ivWithdrawalClose.setOnClickListener { finish() }
        btnUse.setOnClickListener { finish() }
        btnUnUse.setOnClickListener { withdrawalViewModel.deleteUser() }
        checkboxAgree.setOnClickListener { setButtonState() }
    }

    private fun observeWithdrawal() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                withdrawalViewModel.withdrawalStateFlow.collect { withdrawalUiState ->
                    when(withdrawalUiState) {
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
        val intent = Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        finish()
    }

    private fun setButtonState() {
        with(binding) {
            btnUnUse.isEnabled = checkboxAgree.isChecked
            when(checkboxAgree.isChecked) {
                true -> btnUnUse.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this@WithdrawalActivity, Color.brand100))
                false -> btnUnUse.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this@WithdrawalActivity, Color.brand50))
            }
        }
    }
}
