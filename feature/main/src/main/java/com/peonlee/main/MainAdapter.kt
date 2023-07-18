package com.peonlee.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.peonlee.explore.ExploreFragment
import com.peonlee.home.HomeFragment
import com.peonlee.user.UserFragment

class MainAdapter(
    fragment: FragmentActivity
) : FragmentStateAdapter(fragment) {
    private val mainFragments = listOf<Fragment>(
        HomeFragment.getInstance(),
        HomeFragment.getInstance(),
        ExploreFragment.getInstance(),
        UserFragment.getInstance()
    )

    override fun getItemCount() = 4
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> HomeFragment()
            1 -> HomeFragment.getInstance()
            2 -> ExploreFragment.getInstance()
            else -> UserFragment.getInstance()
        }
    }

}
