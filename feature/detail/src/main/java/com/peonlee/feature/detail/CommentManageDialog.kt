package com.peonlee.feature.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.peonlee.core.ui.Navigator
import com.peonlee.data.review.ReviewRepository
import com.peonlee.feature.detail.databinding.BottomSheetManageReviewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CommentManageDialog : BottomSheetDialogFragment() {
    companion object {
        private const val ARGUMENT_PRODUCT_EXTRA = "product_extra"
        private const val ARGUMENT_COMMENT_CONTENT = "comment_content"
        fun newInstance(productExtra: ProductExtra, content: String): CommentManageDialog {
            return CommentManageDialog().apply {
                arguments = bundleOf(
                    ARGUMENT_PRODUCT_EXTRA to productExtra,
                    ARGUMENT_COMMENT_CONTENT to content
                )
            }
        }
    }

    private lateinit var binding: BottomSheetManageReviewBinding

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var reviewRepository: ReviewRepository

    private val productExtra by lazy { arguments?.getParcelable<ProductExtra>(ARGUMENT_PRODUCT_EXTRA) }
    private val content by lazy { arguments?.getString(ARGUMENT_COMMENT_CONTENT) ?: "" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetManageReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            llEditReview.setOnClickListener {
                productExtra?.run {
                    navigator.navigateToEditReview(requireContext(), id, imageUrl, name, price, content)
                    dismiss()
                }
            }
            llDeleteReview.setOnClickListener {
                productExtra?.run {
                    viewLifecycleOwner.lifecycleScope.launch {
                        reviewRepository.deleteReview(id)
                        dismiss()
                    }
                }
            }
        }
    }
}
