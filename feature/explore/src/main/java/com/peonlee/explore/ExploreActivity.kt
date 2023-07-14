package com.peonlee.explore

import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.explore.databinding.ActivityExploreActivityBinding

class ExploreActivity : BaseActivity<ActivityExploreActivityBinding>() {
    override fun bindingFactory(): ActivityExploreActivityBinding = ActivityExploreActivityBinding.inflate(layoutInflater)

    override fun initViews() = with(binding) {
        etExplore.addTextChangedListener { input -> ivTextCleaer.isVisible = input?.isNotEmpty() ?: false }
        tvExploreCancel.setOnClickListener { finish() }
        ivTextCleaer.setOnClickListener { etExplore.setText("") }
        ivSearch.setOnClickListener { }
    }
}
