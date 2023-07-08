package com.peonlee.home

import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.peonlee.core.ui.base.BaseFragment
import com.peonlee.home.adapter.HomeAdapter
import com.peonlee.home.databinding.FragmentHomeBinding
import com.peonlee.home.model.divider.DividerUiModel
import com.peonlee.home.model.product.EVENT_PRODUCTS_DUMMY
import com.peonlee.home.model.product.NEW_PRODUCTS
import com.peonlee.home.model.product.POP_PRODUCTS
import com.peonlee.home.model.review.RECENT_REVIEW
import com.peonlee.home.model.title.TitleUiModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val homeViewModel: HomeViewModel by viewModels()
    override fun bindingFactory(parent: ViewGroup): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater, parent, false)
    }

    override fun initViews() {
        val adapter = HomeAdapter()
        binding.rvHome.adapter = adapter

        homeViewModel.products.flowWithLifecycle(
            viewLifecycleOwner.lifecycle
        ).onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    companion object {
        fun getInstance(): HomeFragment = HomeFragment()
    }
}

private val DUMMY = listOf(
    TitleUiModel(
        id = -1,
        title = "주목할 신상"
    )
) + NEW_PRODUCTS + listOf(
    TitleUiModel(
        id = -2,
        title = "꾸준한 인기상품이에요"
    )
) + POP_PRODUCTS + listOf(
    DividerUiModel(id = -3),
    TitleUiModel(id = -4, title = "지금 행사 중!")
) + EVENT_PRODUCTS_DUMMY + listOf(
    DividerUiModel(id = -5),
    TitleUiModel(id = -6, title = "최근 리뷰")
) + RECENT_REVIEW
