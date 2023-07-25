package com.peonlee.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.peonlee.evaluate.EvaluateFragment
import com.peonlee.explore.ExploreFragment
import com.peonlee.home.HomeFragment
import com.peonlee.user.UserFragment

/**
 * 주의
 * FragmentPagerAdapter 는 특정 생성자만 deprecated 되었습니다.
 */
class MainAdapter(
    fragment: FragmentActivity
) : FragmentStateAdapter(fragment) {
    private val mainFragments = listOf<Fragment>(
        HomeFragment.getInstance(),
        EvaluateFragment.getInstance(),
        ExploreFragment.getInstance(),
        UserFragment.getInstance()
    )

    override fun getItemCount(): Int = mainFragments.size
    override fun createFragment(position: Int): Fragment {
        return mainFragments[position]
    }
}
