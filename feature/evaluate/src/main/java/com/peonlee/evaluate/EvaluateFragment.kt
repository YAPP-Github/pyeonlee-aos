package com.peonlee.evaluate

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.peonlee.common.ext.dpToPx
import com.peonlee.core.ui.base.BaseFragment
import com.peonlee.core.ui.base.Navigatable
import com.peonlee.core.ui.extensions.showToast
import com.peonlee.core.ui.util.spannable.setTextSpannable
import com.peonlee.evaluate.databinding.FragmentEvaluateBinding
import com.peonlee.evaluate.databinding.LayoutEvaluateSnackbarBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.peonlee.core.ui.R.color as Color
import com.peonlee.core.ui.R.drawable as Drawable
import com.peonlee.evaluate.R.string as EvaluateString

@AndroidEntryPoint
class EvaluateFragment : BaseFragment<FragmentEvaluateBinding>(), SwipeCallbackListener {
    private val viewModel: EvaluateViewModel by viewModels()
    private val evaluateAdapter: EvaluateAdapter = EvaluateAdapter()
    private val undoSnackBar: Snackbar by lazy { showSnackBar() }
    override fun bindingFactory(inflater: LayoutInflater, parent: ViewGroup?): FragmentEvaluateBinding {
        return FragmentEvaluateBinding.inflate(
            inflater,
            parent,
            false
        )
    }

    // TODO : 가독성 좋게 리팩토링
    override fun initViews() = with(binding) {
        observable()
        setEvaluateCountSpannable()

        val isFromOnboard = arguments?.getBoolean(FROM_ONBOARDING_FLAG) ?: false

        layoutGuide.apply {
            isVisible = isFromOnboard
            setOnClickListener { hideGuide() }
        }

        tvNext.apply {
            isVisible = isFromOnboard
            setOnClickListener {
                if (viewModel.evaluateCount >= EVALUATE_PRODUCT_COUNT) {
                    moveToNextPage()
                } else {
                    requireActivity().showToast(R.string.evaluate_count)
                }
            }
        }

        rvProductList.apply {
            adapter = evaluateAdapter
            val scrollListener = object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    when (newState) {
                        RecyclerView.SCROLL_STATE_DRAGGING -> {
                            hideGuide()
                            if (undoSnackBar.isShown) {
                                undoSnackBar.dismiss()
                            }
                        }
                    }
                }
            }
            addOnScrollListener(scrollListener)
        }
        ItemTouchHelper(
            SwipeCallback(
                context = requireContext(),
                swipeCallbackListener = this@EvaluateFragment
            )
        ).attachToRecyclerView(rvProductList)
    }

    /**
     * 메인 화면으로 이동
     */
    private fun moveToNextPage() {
        (requireActivity() as? Navigatable)?.moveToNextPage()
    }

    private fun observable() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.productFlow.collectLatest { evaluatePagingData ->
                        evaluateAdapter.submitData(viewLifecycleOwner.lifecycle, evaluatePagingData)
                    }
                }

                launch {
                    viewModel.evaluateState.collect { evaluateProductUiState ->
                        when (evaluateProductUiState) {
                            is EvaluateProductUiState.Like -> evaluateProduct()
                            is EvaluateProductUiState.Dislike -> evaluateProduct()
                            is EvaluateProductUiState.Undo -> undoProduct()
                            is EvaluateProductUiState.EvaluateFail -> requireActivity().showToast(evaluateProductUiState.exception.toString())
                        }
                    }
                }
            }
        }
    }

    override fun onSwipe(
        position: Int,
        direction: Int
    ) {
        viewModel.setLastEvaluateItem(evaluateAdapter.snapshot().items[position])
        hideGuide()

        when (direction) {
            LIKE -> {
                viewModel.apply {
                    setLikeType("LIKE")
                    likeProduct(evaluateAdapter.snapshot().items[position].productId)
                }
            }

            DISLIKE -> {
                viewModel.apply {
                    setLikeType("DISLIKE")
                    dislikeProduct(evaluateAdapter.snapshot().items[position].productId)
                }
            }
        }
    }

    private fun showSnackBar(): Snackbar {
        val snackBar = Snackbar.make(binding.layoutEvaluate, "", SNACKBAR_DURATION)
        val marginFromSides = SNACKBAR_SIDE.dpToPx(requireContext())
        val marginFromBottom = SNACKBAR_BOTTOM.dpToPx(requireContext())
        val height = SNACKBAR_HEIGHT.dpToPx(requireContext())

        snackBar.view.apply {
            setBackgroundResource(Drawable.bg_white_radius_10dp)
            backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    requireContext(),
                    Color.bg80
                )
            )
        }

        val snackView = LayoutEvaluateSnackbarBinding.inflate(layoutInflater)
        val snackBarView = snackBar.view as Snackbar.SnackbarLayout
        val parentParams = snackBarView.layoutParams as ViewGroup.MarginLayoutParams

        parentParams.apply {
            setMargins(marginFromSides, marginFromBottom, marginFromSides, marginFromBottom)
            this.height = height
        }

        snackBar.view.layoutParams = parentParams
        snackBarView.addView(snackView.root, 0)

        snackView.apply {
            textView.text = getString(EvaluateString.evaluate_snackbar_title)
            tvUndo.text = getString(EvaluateString.evaluate_snackbar_undo)

            snackView.tvUndo.setOnClickListener {
                viewModel.apply {
                    setLikeType("UNDO")
                    undoProduct(viewModel.lastEvaluateItem.productId)
                }
            }
        }
        return snackBar
    }

    private fun setEvaluateCountSpannable() {
        binding.tvEvaluate.text = setTextSpannable(
            end = EVALUATE_TEXT_SIZE + viewModel.evaluateCount.toString().length,
            context = requireContext(),
            contents = String.format(getString(EvaluateString.evaluate_product_count), viewModel.evaluateCount)
        )
    }

    private fun evaluateProduct() {
        viewModel.evaluateProductItem(viewModel.lastEvaluateItem)
        setEvaluateCountSpannable()
        undoSnackBar.show()
    }

    private fun undoProduct() {
        viewModel.undoProductItem(viewModel.lastEvaluateItem)
        setEvaluateCountSpannable()
        undoSnackBar.dismiss()
    }

    private fun hideGuide() {
        binding.layoutGuide.isVisible = false
    }

    companion object {
        private const val SNACKBAR_DURATION = 8000
        private const val SNACKBAR_HEIGHT = 50
        private const val SNACKBAR_SIDE = 20
        private const val SNACKBAR_BOTTOM = 16

        private const val EVALUATE_TEXT_SIZE = 2
        private const val EVALUATE_PRODUCT_COUNT = 10

        private const val LIKE = 8
        private const val DISLIKE = 4

        private const val FROM_ONBOARDING_FLAG = "onboarding"

        fun getInstance(fromOnboard: Boolean = false): EvaluateFragment {
            val bundle = Bundle()
            bundle.putBoolean(FROM_ONBOARDING_FLAG, fromOnboard)

            return EvaluateFragment().apply {
                arguments = bundle
            }
        }
    }
}
