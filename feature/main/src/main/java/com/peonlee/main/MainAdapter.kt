package com.peonlee.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.peonlee.evaluate.EvaluateFragment
import com.peonlee.explore.ExploreFragment
import com.peonlee.home.HomeFragment
import com.peonlee.user.UserFragment
import java.lang.RuntimeException

/**
 * 주의
 * FragmentPagerAdapter 는 특정 생성자만 deprecated 되었습니다.
 */
class MainAdapter(
    private val factory: FragmentFactory,
    private val fragment: FragmentActivity
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4
    override fun createFragment(position: Int): Fragment {
        val classLoader = fragment.classLoader
        return when (position) {
            0 -> factory.instantiate(classLoader, HomeFragment::class.java.name)
            1 -> factory.instantiate(classLoader, EvaluateFragment::class.java.name)
            2 -> factory.instantiate(classLoader, ExploreFragment::class.java.name)
            3 -> factory.instantiate(classLoader, UserFragment::class.java.name)
            else -> throw RuntimeException("ViewPager Error")
        }
    }
}
