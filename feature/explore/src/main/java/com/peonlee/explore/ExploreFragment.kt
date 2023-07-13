package com.peonlee.explore

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.peonlee.core.ui.adapter.decoration.ContentPaddingDecoration
import com.peonlee.core.ui.adapter.product.ProductAdapter
import com.peonlee.core.ui.base.BaseBottomSheetFragment
import com.peonlee.core.ui.base.BaseFragment
import com.peonlee.data.model.request.ProductSearchRequest
import com.peonlee.explore.databinding.FragmentExploreBinding
import com.peonlee.explore.ui.CategoryFilterBottomSheetFragment
import com.peonlee.explore.ui.EventFilterBottomSheetFragment
import com.peonlee.explore.ui.PriceFilterBottomSheetFragment
import com.peonlee.model.product.PRODUCTS_TEST_DOUBLE
import com.peonlee.model.type.SortType
import com.peonlee.model.util.PaddingValues
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : BaseFragment<FragmentExploreBinding>() {
    private val exploreViewModel: ExploreViewModel by viewModels()

    private var currentBottomSheet: BaseBottomSheetFragment? = null

    override fun bindingFactory(parent: ViewGroup): FragmentExploreBinding {
        return FragmentExploreBinding.inflate(layoutInflater, parent, false)
    }

    override fun initViews() = with(binding) {
        // 상단 상품 정렬 tab 설정
        SortType.values().forEach {
            tabProductSort.addTab(
                tabProductSort.newTab().apply { text = it.uiNameForExplore }
            )
        }
        tabProductSort.addOnTabSelectedListener(onTabSelectedListener)

        // 상품 리스트
        val productAdapter = ProductAdapter(
            rootLayoutParams = ConstraintLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        )
        rvProduct.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = productAdapter
            addItemDecoration(
                ContentPaddingDecoration(
                    PaddingValues(right = 4, bottom = 12, left = 4)
                )
            )
        }
        productAdapter.submitList(PRODUCTS_TEST_DOUBLE)

        binding.let {
            chipPriceFilter.setOnClickListener { showFilterBottomSheet(Filter.PRICE) }
            chipEventFilter.setOnClickListener { showFilterBottomSheet(Filter.EVENT) }
            chipCategoryFilter.setOnClickListener { showFilterBottomSheet(Filter.CATEGORY) }
        }
    }

    private val onTabSelectedListener = object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab) {}
        override fun onTabUnselected(tab: TabLayout.Tab) {}
        override fun onTabSelected(tab: TabLayout.Tab) {
            /**
             * 정렬 타입 변경
             */
            val sortPos = tab.position
            exploreViewModel.setProductSortType(SortType.values()[sortPos])
        }
    }

    private fun showFilterBottomSheet(filter: Filter) {
        currentBottomSheet = (when (filter) {
            Filter.PRICE -> PriceFilterBottomSheetFragment()
            Filter.EVENT -> EventFilterBottomSheetFragment()
            Filter.CATEGORY -> CategoryFilterBottomSheetFragment()
        })
        currentBottomSheet
            ?.setOnCompleteListener(::requestProduct)
            ?.show(childFragmentManager, "Filter")
    }

    private fun requestProduct(request: ProductSearchRequest) {
        currentBottomSheet?.dismiss()
        currentBottomSheet = null
        println(request)
    }

    private enum class Filter { PRICE, EVENT, CATEGORY }

    companion object {
        fun getInstance(): ExploreFragment = ExploreFragment()
    }
}
