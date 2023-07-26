package com.peonlee.product

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.peonlee.core.ui.Navigator
import com.peonlee.core.ui.adapter.decoration.ContentPaddingDecoration
import com.peonlee.core.ui.base.BaseBottomSheetFragment
import com.peonlee.core.ui.base.BaseFragment
import com.peonlee.core.ui.base.ProductSearchableViewModel
import com.peonlee.model.product.ProductSearchConditionUiModel
import com.peonlee.model.type.SortType
import com.peonlee.model.type.toRangeString
import com.peonlee.model.util.PaddingValues
import com.peonlee.product.adapter.FilterAdapter
import com.peonlee.product.adapter.ProductPagingAdapter
import com.peonlee.product.databinding.FragmentProductBinding
import com.peonlee.product.model.BaseFilter
import com.peonlee.product.model.baseFilterSet
import com.peonlee.product.ui.CategoryFilterBottomSheetFragment
import com.peonlee.product.ui.EventFilterBottomSheetFragment
import com.peonlee.product.ui.PriceFilterBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProductFragment : BaseFragment<FragmentProductBinding>() {
    @Inject
    lateinit var navigator: Navigator

    private val exploreViewModel: ProductSearchableViewModel by activityViewModels()
    private val productViewModel: ProductViewModel by viewModels()

    private var currentBottomSheet: BaseBottomSheetFragment? = null
    private val priceFilter by lazy {
        PriceFilterBottomSheetFragment(productViewModel::setPriceFilter)
    }
    private val eventFilter by lazy {
        EventFilterBottomSheetFragment(productViewModel::setEventFilter)
    }
    private val categoryFilter by lazy {
        CategoryFilterBottomSheetFragment(productViewModel::setCategoryFilter)
    }

    private val filterAdapter = FilterAdapter {
        if (it == BaseFilter.Init) {
            productViewModel.setInitProductSearchCondition()
        } else {
            showBottomSheet(it)
        }
    }

    override fun bindingFactory(inflater: LayoutInflater, parent: ViewGroup?): FragmentProductBinding {
        return FragmentProductBinding.inflate(inflater, parent, false)
    }

    override fun initViews() = with(binding) {
        observeKeyword()

        SortType.values().forEach {
            tabProductSort.addTab(
                tabProductSort.newTab().apply { text = it.uiNameForExplore }
            )
        }
        tabProductSort.addOnTabSelectedListener(onTabSelectedListener)

        // 상품 리스트
        val productAdapter = ProductPagingAdapter(
            rootLayoutParams = ConstraintLayout.LayoutParams(
                MATCH_PARENT,
                WRAP_CONTENT
            ),
            navigator
        )
        productAdapter.addLoadStateListener { loadState ->
            if (loadState.append.endOfPaginationReached) {
                setEmptyView(visible = productAdapter.itemCount == 0)
            } else {
                setEmptyView(visible = false)
            }
        }
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
        productViewModel.productSearchCondition.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                currentBottomSheet?.dismiss()
                binding.tabProductSort.getTabAt(it.sortedBy.ordinal)?.select()
                setFilterView(it)
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        // 상품 flow
        productViewModel.products.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                productAdapter.submitData(it)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
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
            productViewModel.setProductSortType(SortType.values()[sortPos])
        }
    }

    private fun setEmptyView(visible: Boolean) = with(binding) {
        tvEmptyProductDescription.isVisible = visible
        tvEmptyProductTitle.isVisible = visible
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

        // 아무것도 선택되지 않았을 때경
        if (priceFilter == null && storeFilters == null && eventFilters == null && categories == null) {
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
        currentBottomSheet
            ?.setChangedFilter(productViewModel.productSearchCondition.value)
            ?.show(childFragmentManager, "Filter")
    }

    private fun observeKeyword() {
        println(exploreViewModel)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                exploreViewModel.productSearchCondition.collect {
                    productViewModel.setProductSearchCondition(it)
                }
            }
        }
    }

    companion object {
        fun getInstance(): ProductFragment = ProductFragment()
    }
}
