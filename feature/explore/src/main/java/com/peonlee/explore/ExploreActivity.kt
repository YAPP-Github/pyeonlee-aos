package com.peonlee.explore

import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.core.ui.extensions.hideKeyboard
import com.peonlee.core.ui.extensions.trim
import com.peonlee.explore.databinding.ActivityExploreActivityBinding
import com.peonlee.product.ProductFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreActivity : BaseActivity<ActivityExploreActivityBinding>() {
    override fun bindingFactory(): ActivityExploreActivityBinding = ActivityExploreActivityBinding.inflate(layoutInflater)

    override fun initViews() = with(binding) {
        etExploreBar.addTextChangedListener { input -> ivTextCleaer.isVisible = input?.isNotEmpty() ?: false }
        tvExploreCancel.setOnClickListener { finish() }
        ivTextCleaer.setOnClickListener { etExploreBar.setText("") }
        ivSearch.setOnClickListener {
            binding.etExploreBar.hideKeyboard()
            attachProduct()
        }
    }

    private fun attachProduct() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.layout_search_product, ProductFragment(binding.etExploreBar.trim()))
            .commit()
    }
}
