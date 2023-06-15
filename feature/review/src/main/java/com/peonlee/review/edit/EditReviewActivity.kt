package com.peonlee.review.edit

import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.review.R
import com.peonlee.review.databinding.ActivityEditReviewBinding
import kotlinx.coroutines.launch


class EditReviewActivity : BaseActivity<ActivityEditReviewBinding>() {

    private val editReviewViewModel = EditReviewViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initState()
    }

    override fun bindingFactory(): ActivityEditReviewBinding {
        return ActivityEditReviewBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        with(binding) {
            tvProductName.text = "코카)코카제로레몬캔355ml"
            tvProductPrice.text = "2,000원"
            editReview.doOnTextChanged { text, _, _, _ ->
                editReviewViewModel.setReview(text?.toString())
            }
        }
    }

    private fun initState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                editReviewViewModel.review.collect { review ->
                    binding.tvTextCount.text = getString(
                        R.string.edit_review_text_count,
                        review.length,
                        EditReviewViewModel.REVIEW_MAX_LENGTH
                    )
                }
            }
        }
    }
}
