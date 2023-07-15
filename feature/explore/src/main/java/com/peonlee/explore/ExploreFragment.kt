package com.peonlee.explore

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.peonlee.core.ui.adapter.decoration.ContentPaddingDecoration
import com.peonlee.core.ui.adapter.product.ProductAdapter
import com.peonlee.core.ui.base.BaseBottomSheetFragment
import com.peonlee.core.ui.base.BaseFragment
import com.peonlee.explore.adapter.FilterAdapter
import com.peonlee.explore.databinding.FragmentExploreBinding
import com.peonlee.explore.model.BaseFilter
import com.peonlee.explore.model.ProductSearchConditionUiModel
import com.peonlee.explore.model.baseFilterSet
import com.peonlee.explore.ui.CategoryFilterBottomSheetFragment
import com.peonlee.explore.ui.EventFilterBottomSheetFragment
import com.peonlee.explore.ui.PriceFilterBottomSheetFragment
import com.peonlee.model.type.SortType
import com.peonlee.model.type.toRangeString
import com.peonlee.model.util.PaddingValues
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ExploreFragment : BaseFragment<FragmentExploreBinding>() {
    private val exploreViewModel: ExploreViewModel by viewModels()

    private var currentBottomSheet: BaseBottomSheetFragment? = null
    private val priceFilter by lazy {
        PriceFilterBottomSheetFragment(exploreViewModel::setPriceFilter)
    }
    private val eventFilter by lazy {
        EventFilterBottomSheetFragment(exploreViewModel::setEventFilter)
    }
    private val categoryFilter by lazy {
        CategoryFilterBottomSheetFragment(exploreViewModel::setCategoryFilter)
    }

    private val filterAdapter = FilterAdapter { filter ->
        showBottomSheet(filter)
    }

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

        layoutFilter.adapter = filterAdapter
        layoutFilter.addItemDecoration(ContentPaddingDecoration(PaddingValues(left = 2, right = 2)))
        // 검색 조건 변경 시
        exploreViewModel.productSearchCondition.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                currentBottomSheet?.dismiss()
                setFilterView(it)
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        Unit
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

    private fun setFilterView(productSearchCondition: ProductSearchConditionUiModel) {
        val priceFilter = productSearchCondition.price?.let { BaseFilter.Price(title = it.toRangeString(requireContext()), isSelected = true) }
        val storeFilters = productSearchCondition.stores?.let { stores ->
            stores.map { BaseFilter.Event(title = it.storeName, isSelected = true) }
        }
        val eventFilters = productSearchCondition.events?.let { events ->
            events.map { BaseFilter.Event(title = it.eventName, isSelected = true) }
        }
        val categories = productSearchCondition.categories?.let { categories ->
            categories.map { BaseFilter.Category(title = it.categoryName, isSelected = true) }
        }

        // 아무것도 선택되지 않았을 때
        if (priceFilter == null && eventFilters == null && categories == null) {
            filterAdapter.submitList(baseFilterSet)
        } else {
            val filterList = mutableListOf<BaseFilter>(BaseFilter.Init)
            filterList += priceFilter ?: BaseFilter.Price()
            if (storeFilters != null) filterList += storeFilters
            if (eventFilters != null) filterList += eventFilters
            if (storeFilters == null && eventFilters == null) filterList += BaseFilter.Event()
            filterList += categories ?: listOf(BaseFilter.Category())
            filterAdapter.submitList(filterList)
        }
    }

    private fun showBottomSheet(baseFilter: BaseFilter) {
        currentBottomSheet = when (baseFilter) {
            is BaseFilter.Category -> categoryFilter
            is BaseFilter.Event -> eventFilter
            BaseFilter.Init -> null
            is BaseFilter.Price -> priceFilter
        }
        currentBottomSheet?.show(childFragmentManager, "Filter")
    }

    companion object {
        fun getInstance(): ExploreFragment = ExploreFragment()
    }
}
