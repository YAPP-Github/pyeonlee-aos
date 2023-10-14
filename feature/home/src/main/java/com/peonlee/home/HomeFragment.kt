package com.peonlee.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.peonlee.core.ui.Navigator
import com.peonlee.core.ui.base.BaseFragment
import com.peonlee.core.ui.base.ProductSearchableViewModel
import com.peonlee.home.adapter.HomeAdapter
import com.peonlee.home.databinding.FragmentHomeBinding
import com.peonlee.home.model.button.ButtonType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    @Inject
    lateinit var navigator: Navigator

    private val homeViewModel: HomeViewModel by viewModels()
    private val productSearchableViewModel: ProductSearchableViewModel by activityViewModels()

    private val homeAdapter by lazy {
        HomeAdapter(
            navigator = navigator,
            moveToConditionExplore = productSearchableViewModel::changeSortType,
            moveToStoreExplore = productSearchableViewModel::changeStoreType,
            onClickButton = {
                when (it) {
                    ButtonType.EVENT -> context?.let { navigator.navigateToEvent(it) }
                }
            }
        )
    }

    override fun bindingFactory(inflater: LayoutInflater, parent: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, parent, false)
    }

    override fun initViews() {
        binding.rvHome.adapter = homeAdapter
        binding.btnSearch.setOnClickListener {
            navigator.navigateToSearch(requireContext())
        }

        homeViewModel.products.flowWithLifecycle(
            viewLifecycleOwner.lifecycle
        ).onEach {
            homeAdapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    companion object {
        fun getInstance(): HomeFragment = HomeFragment()
    }
}
