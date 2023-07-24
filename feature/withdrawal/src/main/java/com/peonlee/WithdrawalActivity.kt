package com.peonlee

import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.withdrawal.databinding.ActivityWithdrawalBinding

class WithdrawalActivity : BaseActivity<ActivityWithdrawalBinding>() {
    override fun bindingFactory() = ActivityWithdrawalBinding.inflate(layoutInflater)
}
