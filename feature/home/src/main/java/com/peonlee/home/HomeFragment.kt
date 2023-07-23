package com.peonlee.home

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

    override fun bindingFactory(parent: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        val adapter = HomeAdapter(
            navigator = navigator,
            moveToConditionExplore = productSearchableViewModel::changeSortType,
            moveToStoreExplore = productSearchableViewModel::changeStoreType
        )
        binding.rvHome.adapter = adapter
        binding.btnSearch.setOnClickListener {
            navigator.navigateToSearch(requireContext())
        }

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
