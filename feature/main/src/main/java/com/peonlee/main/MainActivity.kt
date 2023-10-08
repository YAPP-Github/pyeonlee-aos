package com.peonlee.main

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.core.ui.base.PageActivity
import com.peonlee.core.ui.viewmodel.ProductViewModel
import com.peonlee.data.product.ProductRepository
import com.peonlee.main.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(), PageActivity {
    @Inject
    lateinit var productRepository: ProductRepository
    private val productViewModel: ProductViewModel by viewModels { ProductViewModel.ProductViewModelFactory(productRepository) }
    private val mainViewModel: MainViewModel by viewModels { MainViewModel.MainViewModelFactory() }

    override fun initViews() {
        productViewModel
        binding.bottomNav.setOnItemSelectedListener {
            (mainViewModel as? MainViewModel)?.changeSelectedNav(it.itemId)
            true
        }

        binding.layoutFragment.isUserInputEnabled = false
        binding.layoutFragment.adapter = MainAdapter(
            factory = supportFragmentManager.fragmentFactory,
            fragment = this
        )
    }

    override fun bindViews() {
        (mainViewModel as? MainViewModel)?.selectedNav?.flowWithLifecycle(lifecycle)
            ?.onEach {
                binding.bottomNav.selectedItemId = it
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

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        for (i in 0 until count) supportFragmentManager.popBackStack()
        super.onBackPressed()
    }

    override fun moveToEvaluatePage() {
        mainViewModel.changeSelectedNav(R.id.navExplore)
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(
                Intent(context, MainActivity::class.java)
            )
        }
    }
}
