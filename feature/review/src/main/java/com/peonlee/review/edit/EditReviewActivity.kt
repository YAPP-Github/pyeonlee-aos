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
        }
    }

    override fun bindViews() {
        with(binding) {
            editReview.doOnTextChanged { text, _, _, _ ->
                editReviewViewModel.setReview(text?.toString())
            }
            // 등록 하기 버튼 클릭
            btnSave.setOnClickListener { editReviewViewModel.saveReview() }
            // 상단 X 버튼 클릭
            btnClose.setOnClickListener { finish() }
        }
    }

    private fun initState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                editReviewViewModel.review.collect { review ->
                    // 리뷰 입력 시, 하단 리뷰 글자 수 변경
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
