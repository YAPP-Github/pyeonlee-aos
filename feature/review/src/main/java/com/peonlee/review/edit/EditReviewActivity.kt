package com.peonlee.review.edit

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.text.InputFilter
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import androidx.activity.viewModels
import androidx.core.view.doOnLayout
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.core.ui.extensions.focus
import com.peonlee.core.ui.util.keyboard.KeyboardVisibilityEvent
import com.peonlee.core.ui.util.keyboard.KeyboardVisibilityEventListener
import com.peonlee.review.R
import com.peonlee.review.databinding.ActivityEditReviewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@AndroidEntryPoint
class EditReviewActivity : BaseActivity<ActivityEditReviewBinding>() {
    private val editReviewViewModel: EditReviewViewModel by viewModels()

    override fun bindingFactory(): ActivityEditReviewBinding {
        return ActivityEditReviewBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        with(binding) {
            // TODO 이후 비지 니스 로직 구현 이슈 에서 수정 예정
            tvProductName.text = "코카)코카제로레몬캔355ml"
            tvProductPrice.text = "2,000원"
            // 리뷰 작성 editor 의 최대 작성 길이 제한(300자)
            editReview.filters = arrayOf(
                InputFilter.LengthFilter(REVIEW_MAX_LENGTH)
            )
        }
    }

    override fun onStart() {
        super.onStart()
        // 화면에 처음 들어 오면 리뷰 작성 란으로 auto focusing
        binding.editReview.doOnLayout {
            (it as? EditText)?.focus()
        }
    }

    override fun bindViews() {
        with(binding) {
            initState()
            // 리뷰 작성 란 클릭 시, editor 에 focusing
            layoutEditReview.setOnClickListener { editReview.focus() }
            // editor 작성 시 viewModel 의 state update
            editReview.doOnTextChanged { text, _, _, _ ->
                editReviewViewModel.setReview(text?.toString())
            }
            // 등록 하기 버튼 클릭
            btnSave.setOnClickListener { editReviewViewModel.saveReview() }
            btnClose.setOnClickListener { finish() } // 상단 X 버튼 클릭
            // editor focusing 변경
            editReview.onFocusChangeListener = reviewEditorFocusChangedListener
            // 키보드 show / hide event listener
            KeyboardVisibilityEvent.setEventListener(
                activity = this@EditReviewActivity,
                lifecycleOwner = this@EditReviewActivity,
                listener = object : KeyboardVisibilityEventListener {
                    override fun onVisibilityChanged(isOpen: Boolean) {
                        // 키보드 가 화면 에서 제거 되면 editor 의 focus 제거
                        if (isOpen.not()) {
                            binding.editReview.clearFocus()
                        }
                    }
                }
            )
        }
    }

    /**
     * 리뷰 작성 editor 의 focus 가 맞춰질 때마다 stroke 색 변경
     */
    private val reviewEditorFocusChangedListener = object : OnFocusChangeListener {
        override fun onFocusChange(view: View?, focused: Boolean) {
            val backgroundTint = if (focused) {
                com.peonlee.core.ui.R.color.brand100
            } else {
                com.peonlee.core.ui.R.color.bg20
            }
            (binding.layoutEditReview.background as? GradientDrawable)?.apply {
                setStroke(
                    1.dpToPx(this@EditReviewActivity),
                    getColor(backgroundTint)
                )
            }
        }
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
                    val btnColor = getColor(
                        if (it.length in REVIEW_MIN_LENGTH..REVIEW_MAX_LENGTH) {
                            com.peonlee.core.ui.R.color.brand100
                        } else {
                            com.peonlee.core.ui.R.color.brand50
                        }
                    )
                    // 작성한 리뷰의 길이가 최소 <= length <= 최대인 경우 버튼 색상 변경
                    binding.btnSave.backgroundTintList = ColorStateList.valueOf(btnColor)
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
        const val REVIEW_MIN_LENGTH = 10 // 최소 리뷰 길이
        const val REVIEW_MAX_LENGTH = 300 // 최대 리뷰 길이
    }
}
