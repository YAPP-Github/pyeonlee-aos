package com.peonlee.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
<<<<<<< HEAD
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
=======
>>>>>>> af4bc32f1ba80a5b187d7895c7834202e4cf92a8
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.peonlee.explore.ExploreFragment
import com.peonlee.home.HomeFragment
import com.peonlee.user.UserFragment

<<<<<<< HEAD
/**
 * 주의
 * FragmentPagerAdapter 는 특정 생성자만 deprecated 되었습니다.
 */
class MainAdapter(
    private val fragment: FragmentActivity
) : FragmentStateAdapter(fragment) {
    private val fragments = listOf(
=======
class MainAdapter(
    fragment: FragmentActivity
) : FragmentStateAdapter(fragment) {
    private val mainFragments = listOf<Fragment>(
>>>>>>> af4bc32f1ba80a5b187d7895c7834202e4cf92a8
        HomeFragment.getInstance(),
        HomeFragment.getInstance(),
        ExploreFragment.getInstance(),
        UserFragment.getInstance()
    )
<<<<<<< HEAD
    override fun getItemCount(): Int = fragments.size
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
=======

    override fun getItemCount() = 4
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> HomeFragment()
            1 -> HomeFragment.getInstance()
            2 -> ExploreFragment.getInstance()
            else -> UserFragment.getInstance()
        }
    }

>>>>>>> af4bc32f1ba80a5b187d7895c7834202e4cf92a8
}
