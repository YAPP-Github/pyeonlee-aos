package com.peonlee.home

import android.view.ViewGroup
import com.peonlee.core.ui.base.BaseFragment
import com.peonlee.home.adapter.HomeAdapter
import com.peonlee.home.databinding.FragmentHomeBinding
import com.peonlee.model.MainHomeViewType
import com.peonlee.model.title.BaseTitleUiModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun bindingFactory(parent: ViewGroup): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater, parent, false)
    }

    override fun initViews() {
        val adapter = HomeAdapter()
        binding.layoutHome.adapter = adapter
    }

    companion object {
        fun getInstance(): HomeFragment = HomeFragment()
    }
}
