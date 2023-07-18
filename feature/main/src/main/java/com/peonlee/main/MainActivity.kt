package com.peonlee.main

import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.main.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun initViews() {
        binding.bottomNav.setOnItemSelectedListener {
            mainViewModel.changeTab(it.itemId)
            true
        }
        binding.layoutFragment.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.layoutFragment.adapter = MainAdapter(this)
    }

    override fun bindViews() {
        mainViewModel.currentTab.flowWithLifecycle(lifecycle)
            .onEach {
                binding.layoutFragment.currentItem = when (it) {
                    R.id.navHome -> 0
                    else -> 2
                }
            }.launchIn(lifecycleScope)
    }

    override fun bindingFactory(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}
