package com.peonlee.user

import android.view.LayoutInflater
import android.view.ViewGroup
import com.peonlee.core.ui.base.BaseFragment
import com.peonlee.user.databinding.FragmentUserBinding

/**
 * 사용자 마이 페이지 Fragment
 */
class UserFragment : BaseFragment<FragmentUserBinding>() {
    override fun bindingFactory(parent: ViewGroup): FragmentUserBinding {
        return FragmentUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    companion object {
        fun getInstance() = UserFragment()
    }
}
