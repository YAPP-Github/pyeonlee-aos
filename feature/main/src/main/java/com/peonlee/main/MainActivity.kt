package com.peonlee.main

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.core.ui.base.ProductSearchableViewModel
import com.peonlee.evaluate.EvaluateFragment
import com.peonlee.explore.ExploreFragment
import com.peonlee.home.HomeFragment
import com.peonlee.main.databinding.ActivityMainBinding
import com.peonlee.user.UserFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val mainFragments = listOf<Fragment>(
        HomeFragment.getInstance(),
        EvaluateFragment.getInstance(),
        ExploreFragment.getInstance(),
        UserFragment.getInstance()
    )
    private val mainViewModel: ProductSearchableViewModel by viewModels { MainViewModel.MainViewModelFactory() }

    override fun initViews() {
        binding.bottomNav.setOnItemSelectedListener {
            (mainViewModel as? MainViewModel)?.changeSelectedNav(it.itemId)
            true
        }
    }

    private fun changeFragment(fragment: Fragment, tag: String) {
        val stackedFragment = supportFragmentManager.findFragmentByTag(tag)
        supportFragmentManager.commit {
            setReorderingAllowed(false)
            replace(R.id.layout_fragment, stackedFragment ?: fragment, tag)
            addToBackStack(tag)
        }
    }

    override fun bindViews() {
        (mainViewModel as? MainViewModel)?.selectedNav?.flowWithLifecycle(lifecycle)
            ?.onEach {
                binding.bottomNav.selectedItemId = it
                val (fragment, tag) = when (it) {
                    R.id.navHome -> mainFragments[0] to "Home"
                    R.id.navEvaluate -> mainFragments[1] to "Evaluate"
                    R.id.navExplore -> mainFragments[2] to "Explore"
                    else -> mainFragments[3] to "User"
                }
                changeFragment(fragment, tag)
            }?.launchIn(lifecycleScope)
    }

    override fun bindingFactory(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}
