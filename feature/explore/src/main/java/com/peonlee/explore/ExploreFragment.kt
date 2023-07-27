package com.peonlee.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.peonlee.core.ui.Navigator
import com.peonlee.core.ui.base.BaseFragment
import com.peonlee.explore.databinding.FragmentExploreBinding
import com.peonlee.model.product.ProductSearchConditionUiModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ExploreFragment : BaseFragment<FragmentExploreBinding>() {
    @Inject
    lateinit var navigator: Navigator

    override fun bindingFactory(inflater: LayoutInflater, parent: ViewGroup?): FragmentExploreBinding {
        return FragmentExploreBinding.inflate(inflater, parent, false)
    }

    override fun initViews() {
        // 상단 검색 바 클릭 시, 검색 화면으로 이동
        binding.layoutSearch.setOnClickListener {
            navigator.navigateToSearch(requireContext())
        }
    }

    companion object {
        const val SEARCH_CONDITION = "productSearchCondition"

        fun getInstance(): ExploreFragment = ExploreFragment()
        fun getInstance(
            productSearchConditionUiModel: ProductSearchConditionUiModel
        ): ExploreFragment {
            val bundle = Bundle()
            bundle.putSerializable(SEARCH_CONDITION, productSearchConditionUiModel)

            val exploreFragment = ExploreFragment()
            exploreFragment.arguments = bundle
            return exploreFragment
        }
    }
}
