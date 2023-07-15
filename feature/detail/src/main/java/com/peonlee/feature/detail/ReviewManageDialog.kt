package com.peonlee.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.peonlee.core.ui.Navigator
import com.peonlee.feature.detail.databinding.BottomSheetManageReviewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReviewManageDialog : BottomSheetDialogFragment() {
    companion object {
        private const val ARGUMENT_PRODUCT_ID = "product_id"
        fun newInstance(productId: Int) {
            ReviewManageDialog().apply {
                arguments = bundleOf(
                    ARGUMENT_PRODUCT_ID to productId
                )
            }
        }
    }

    private lateinit var binding: BottomSheetManageReviewBinding

    private val viewModel: ProductDetailViewModel by activityViewModels()

    @Inject
    lateinit var navigator: Navigator

    private val productId by lazy { arguments?.getInt(ARGUMENT_PRODUCT_ID) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetManageReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            llEditReview.setOnClickListener {
                with(viewModel.productDetail) {
                    navigator.navigateToEditReview(requireContext(), productId, imageUrl, name, price)
                    dismiss()
                }
            }
            llDeleteReview.setOnClickListener {
                productId?.let {
                    viewModel.deleteReview(it)
                    dismiss()
                }
            }
        }
    }
}
