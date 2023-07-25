package com.peonlee.user

import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.peonlee.core.ui.base.BaseFragment
import com.peonlee.user.databinding.FragmentUserBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * 사용자 마이 페이지 Fragment
 */
@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding>() {
    private val userViewModel: UserViewModel by viewModels()

    override fun bindingFactory(parent: ViewGroup?): FragmentUserBinding {
        return FragmentUserBinding.inflate(
            layoutInflater,
            parent,
            false
        )
    }

    override fun initViews() {
        userViewModel.user.flowWithLifecycle(lifecycle)
            .onEach {
                with(binding) {
                    tvNickname.text = it.nickname
                    // 작성한 리뷰 개수
                    tvCommentCount.text = getString(R.string.count, it.productCommentCount)
                    // 평가한 상품 개수
                    tvEvaluateCount.text = getString(R.string.count, it.productLikeCount)
                }
            }.launchIn(lifecycleScope)
    }

    companion object {
        fun getInstance() = UserFragment()
    }
}
