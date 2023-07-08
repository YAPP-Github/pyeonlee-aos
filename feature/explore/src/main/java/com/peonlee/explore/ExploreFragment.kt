package com.peonlee.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.peonlee.core.ui.adapter.decoration.ContentPaddingDecoration
import com.peonlee.core.ui.adapter.product.ProductAdapter
import com.peonlee.core.ui.base.BaseFragment
import com.peonlee.explore.databinding.FragmentExploreBinding
import com.peonlee.model.product.PRODUCTS_TEST_DOUBLE
import com.peonlee.model.type.SortType
import com.peonlee.model.util.PaddingValues
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : BaseFragment<FragmentExploreBinding>() {
    private val exploreViewModel: ExploreViewModel by viewModels()

    override fun bindingFactory(parent: ViewGroup): FragmentExploreBinding {
        return FragmentExploreBinding.inflate(layoutInflater, parent, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initViews() = with(binding) {
        // 상단 상품 정렬 tab 설정
        SortType.values().forEach {
            tabProductSort.addTab(
                tabProductSort.newTab().apply { text = getString(it.sortName) }
            )
        }
        tabProductSort.addOnTabSelectedListener(onTabSelectedListener)

        // 상품 리스트
        val adapter = ProductAdapter(
            rootLayoutParams = ConstraintLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        )
        rvProduct.layoutManager = GridLayoutManager(context, 2)
        rvProduct.adapter = adapter
        rvProduct.addItemDecoration(
            ContentPaddingDecoration(
                PaddingValues(top = 12, right = 4, bottom = 12, left = 4)
            )
        )
        adapter.submitList(PRODUCTS_TEST_DOUBLE)
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

    companion object {
        fun getInstance(): ExploreFragment = ExploreFragment()
    }
}
