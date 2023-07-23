package com.peonlee.main

import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.core.ui.base.ProductSearchableViewModel
import com.peonlee.main.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val mainViewModel: ProductSearchableViewModel by viewModels { MainViewModel.MainViewModelFactory() }

    override fun initViews() {
        binding.bottomNav.setOnItemSelectedListener {
            (mainViewModel as? MainViewModel)?.changeSelectedNav(it.itemId)
            true
        }
        binding.layoutFragment.isUserInputEnabled = false
        binding.layoutFragment.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.layoutFragment.adapter = MainAdapter(this)
    }

    override fun bindViews() {
        (mainViewModel as? MainViewModel)?.selectedNav?.flowWithLifecycle(lifecycle)
            ?.onEach {
                binding.layoutFragment.currentItem = when (it) {
                    R.id.navHome -> 0
                    R.id.navEvaluate -> 1
                    R.id.navExplore -> 2
                    else -> 3
                }
            }?.launchIn(lifecycleScope)
    }

    override fun bindingFactory(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}
