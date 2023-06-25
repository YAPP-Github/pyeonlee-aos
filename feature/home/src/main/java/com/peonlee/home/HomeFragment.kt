package com.peonlee.home

import android.view.ViewGroup
import com.peonlee.core.ui.base.BaseFragment
import com.peonlee.home.adapter.HomeAdapter
import com.peonlee.home.databinding.FragmentHomeBinding
import com.peonlee.home.model.divider.DividerUiModel
import com.peonlee.home.model.product.EVENT_PRODUCTS_DUMMY
import com.peonlee.home.model.product.NEW_PRODUCTS
import com.peonlee.home.model.product.POP_PRODUCTS
import com.peonlee.home.model.review.RECENT_REVIEW
import com.peonlee.home.model.title.TitleUiModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun bindingFactory(parent: ViewGroup): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater, parent, false)
    }

    override fun initViews() {
        val adapter = HomeAdapter()
        binding.layoutHome.adapter = adapter
        adapter.submitList(DUMMY)
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
