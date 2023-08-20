package com.peonlee.explore

import android.content.Context
import android.content.Intent
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.core.ui.base.ProductSearchableViewModel
import com.peonlee.core.ui.extensions.hideKeyboard
import com.peonlee.core.ui.extensions.trim
import com.peonlee.explore.databinding.ActivityExploreActivityBinding
import com.peonlee.product.ProductFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreActivity : BaseActivity<ActivityExploreActivityBinding>() {
    private val exploreViewModel: ProductSearchableViewModel by viewModels { ExploreViewModel.ExploreViewModelFactory() }
    override fun bindingFactory(): ActivityExploreActivityBinding = ActivityExploreActivityBinding.inflate(layoutInflater)

    override fun initViews()  {
        println(exploreViewModel)
        attachProductFragment()
    }

    override fun bindViews() = with(binding) {
        etExploreBar.addTextChangedListener { input -> ivTextCleaer.isVisible = input?.isNotEmpty() ?: false }
        tvExploreCancel.setOnClickListener { finish() }
        ivTextCleaer.setOnClickListener { etExploreBar.setText("") }
        ivSearch.setOnClickListener { searchResult() }
        etExploreBar.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                searchResult()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun attachProductFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.layout_search_product, ProductFragment.getInstance())
            .commit()
    }

    private fun searchResult() {
        binding.apply {
            layoutSearchProduct.isVisible = true
            (exploreViewModel as? ExploreViewModel)?.setKeyword(etExploreBar.trim())
            etExploreBar.clearFocus()
            etExploreBar.hideKeyboard()
        }
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(
                Intent(context, ExploreActivity::class.java)
            )
        }
    }
}
