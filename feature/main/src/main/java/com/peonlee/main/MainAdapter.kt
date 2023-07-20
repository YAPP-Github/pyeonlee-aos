package com.peonlee.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.peonlee.explore.ExploreFragment
import com.peonlee.home.HomeFragment
import com.peonlee.user.UserFragment

/**
 * 주의
 * FragmentPagerAdapter 는 특정 생성자만 deprecated 되었습니다.
 */
class MainAdapter(
    private val fragment: FragmentActivity
) : FragmentStateAdapter(fragment) {
    private val fragments = listOf(
        HomeFragment.getInstance(),
        HomeFragment.getInstance(),
        ExploreFragment.getInstance(),
        UserFragment.getInstance()
    )
    override fun getItemCount(): Int = fragments.size
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}
