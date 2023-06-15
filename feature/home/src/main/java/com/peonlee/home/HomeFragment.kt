package com.peonlee.home

import android.view.ViewGroup
import com.peonlee.core.ui.base.BaseFragment
import com.peonlee.home.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun bindingFactory(parent: ViewGroup): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater, parent, false)
    }

    companion object {
        fun getInstance(): HomeFragment = HomeFragment()
    }
}
