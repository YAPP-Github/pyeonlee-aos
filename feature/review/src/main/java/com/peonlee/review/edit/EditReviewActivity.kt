package com.peonlee.review.edit

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.text.InputFilter
import androidx.core.view.doOnLayout
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.core.ui.extensions.focus
import com.peonlee.core.ui.util.KeyboardVisibilityEvent
import com.peonlee.core.ui.util.KeyboardVisibilityEventListener
import com.peonlee.review.R
import com.peonlee.review.databinding.ActivityEditReviewBinding
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class EditReviewActivity : BaseActivity<ActivityEditReviewBinding>() {

    private val editReviewViewModel = EditReviewViewModel()

    override fun bindingFactory(): ActivityEditReviewBinding {
        return ActivityEditReviewBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        with(binding) {
            tvProductName.text = "코카)코카제로레몬캔355ml"
            tvProductPrice.text = "2,000원"
            editReview.filters = arrayOf(
                InputFilter.LengthFilter(REVIEW_MAX_LENGTH)
            )
        }
    }

    override fun bindViews() {
        with(binding) {
            editReview.doOnLayout { editReview.focus() }
            editReview.doOnTextChanged { text, _, _, _ ->
                editReviewViewModel.setReview(text?.toString())
            }
            layoutEditReview.setOnClickListener {
                editReview.focus()
            }
            // 등록 하기 버튼 클릭
            btnSave.setOnClickListener { editReviewViewModel.saveReview() }
            // 상단 X 버튼 클릭
            btnClose.setOnClickListener { finish() }
            editReview.setOnFocusChangeListener { _, focused ->
                val backgroundTint = if (focused) com.peonlee.core.ui.R.color.brand100 else com.peonlee.core.ui.R.color.bg20
                (layoutEditReview.background as? GradientDrawable)?.apply {
                    setStroke(
                        1.dpToPx(this@EditReviewActivity),
                        getColor(backgroundTint)
                    )
                }
            }
            KeyboardVisibilityEvent.setEventListener(
                activity = this@EditReviewActivity,
                lifecycleOwner = this@EditReviewActivity,
                listener = object : KeyboardVisibilityEventListener {
                    override fun onVisibilityChanged(isOpen: Boolean) {
                        if (isOpen.not()) {
                            binding.editReview.clearFocus()
                        }
                    }
                }
            )
        }
        initState()
    }

    private fun initState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                editReviewViewModel.review.collect {
                    binding.tvTextCount.text = getString(
                        R.string.edit_review_text_count,
                        it.length,
                        REVIEW_MAX_LENGTH
                    )
                    val backgroundTint = if (it.length in REVIEW_MIN_LENGTH..Int.MAX_VALUE) {
                        com.peonlee.core.ui.R.color.brand100
                    } else {
                        com.peonlee.core.ui.R.color.brand50
                    }
                    binding.btnSave.backgroundTintList = ColorStateList.valueOf(getColor(backgroundTint))
                }
            }
        }
    }

    /**
     * TODO 이후 공통 util 로 분리할 예정
     */
    fun Int.dpToPx(context: Context): Int {
        val density = context.resources.displayMetrics.density
        return (this * density).roundToInt()
    }

    companion object {
        const val REVIEW_MIN_LENGTH = 10
        const val REVIEW_MAX_LENGTH = 300
    }
}
