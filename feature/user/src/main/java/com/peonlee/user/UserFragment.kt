package com.peonlee.user

import android.view.LayoutInflater
import android.view.ViewGroup
import com.peonlee.core.ui.base.BaseFragment
import com.peonlee.user.databinding.FragmentUserBinding

/**
 * 사용자 마이 페이지 Fragment
 */
class UserFragment : BaseFragment<FragmentUserBinding>() {
    override fun bindingFactory(parent: ViewGroup?): FragmentUserBinding {
        return FragmentUserBinding.inflate(
            layoutInflater,
            parent,
            false
        )
    }

    override fun initViews() = with(binding) {
        // TODO 실제 사용자 정보로 대체 필요
        tvNickname.text = "꿈꾸는날다람쥐"
        // 작성한 리뷰 개수
        tvCommentCount.text = getString(R.string.count, 24)
        // 평가한 상품 개수
        tvEvaluateCount.text = getString(R.string.count, 102)
    }

    companion object {
        fun getInstance() = UserFragment()
    }
}
