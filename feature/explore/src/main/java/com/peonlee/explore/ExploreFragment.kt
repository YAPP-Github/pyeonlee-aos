package com.peonlee.explore

import android.view.ViewGroup
import androidx.fragment.app.commit
import com.peonlee.core.ui.Navigator
import com.peonlee.core.ui.base.BaseFragment
import com.peonlee.explore.databinding.FragmentExploreBinding
import com.peonlee.product.ProductFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ExploreFragment : BaseFragment<FragmentExploreBinding>() {
    @Inject
    lateinit var navigator: Navigator

    override fun bindingFactory(parent: ViewGroup?): FragmentExploreBinding {
        return FragmentExploreBinding.inflate(layoutInflater, parent, false)
    }

    override fun initViews() {
        childFragmentManager.commit {
            add(R.id.layoutProduct, ProductFragment.getInstance(), "Product")
        }
    }

    companion object {
        fun getInstance(): ExploreFragment = ExploreFragment()
    }
}
