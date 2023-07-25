package com.peonlee

import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.withdrawal.databinding.ActivityWithdrawalBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WithdrawalActivity : BaseActivity<ActivityWithdrawalBinding>() {
    override fun bindingFactory() = ActivityWithdrawalBinding.inflate(layoutInflater)

    override fun initViews() = with(binding) {
        // TODO 프래그먼트에서 전송된 값 매핑
        layoutReview.tvEvaluateCount.text = ""
        layoutReview.tvReviewCount.text = ""
    }
}
