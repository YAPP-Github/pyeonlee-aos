package com.peonlee

import android.view.ViewGroup
import com.peonlee.core.ui.base.BaseFragment
import com.peonlee.settings.databinding.FragmentSettingBinding

class SettingFragment : BaseFragment<FragmentSettingBinding>() {
    override fun bindingFactory(parent: ViewGroup?): FragmentSettingBinding {
        return FragmentSettingBinding.inflate(layoutInflater, parent, false)
    }

    override fun initViews() {

    }

    companion object {
        fun getInstance() = SettingFragment()
    }
}
