package com.peonlee.review.edit

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.text.InputFilter
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import androidx.activity.viewModels
import androidx.core.view.doOnLayout
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import coil.load
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.core.ui.extensions.focus
import com.peonlee.core.ui.extensions.showToast
import com.peonlee.core.ui.extensions.toFormattedMoney
import com.peonlee.core.ui.util.keyboard.KeyboardVisibilityEvent
import com.peonlee.core.ui.util.keyboard.KeyboardVisibilityEventListener
import com.peonlee.review.R
import com.peonlee.review.databinding.ActivityEditReviewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.math.roundToInt

@AndroidEntryPoint
class EditReviewActivity : BaseActivity<ActivityEditReviewBinding>() {
    companion object {
        private const val REVIEW_MIN_LENGTH = 10 // 최소 리뷰 길이
        private const val REVIEW_MAX_LENGTH = 300 // 최대 리뷰 길이

        private const val EXTRA_PRODUCT_ID = "product_id"
        private const val EXTRA_PRODUCT_IMAGE_URL = "product_image_url"
        private const val EXTRA_PRODUCT_NAME = "product_name"
        private const val EXTRA_PRODUCT_PRICE = "product_price"

        fun startActivity(context: Context, productId: Int, imageUrl: String, productName: String, price: Int) {
            context.startActivity(Intent(context, EditReviewActivity::class.java).apply {
                putExtra(EXTRA_PRODUCT_ID, productId)
                putExtra(EXTRA_PRODUCT_IMAGE_URL, imageUrl)
                putExtra(EXTRA_PRODUCT_NAME, productName)
                putExtra(EXTRA_PRODUCT_PRICE, price)
            })
        }
    }

    private val editReviewViewModel: EditReviewViewModel by viewModels()

    private val productId by lazy { intent.getIntExtra(EXTRA_PRODUCT_ID, -1) }

    override fun bindingFactory(): ActivityEditReviewBinding {
        return ActivityEditReviewBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        if (productId == -1) {
            finish()
            return
        }
        with(binding) {
            ivProductImage.load(intent.getStringExtra(EXTRA_PRODUCT_IMAGE_URL))
            tvProductName.text = intent.getStringExtra(EXTRA_PRODUCT_NAME)
            tvProductPrice.text = intent.getIntExtra(EXTRA_PRODUCT_PRICE, 0).toFormattedMoney()

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
            btnSave.setOnClickListener { editReviewViewModel.saveReview(productId) }
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
    private val reviewEditorFocusChangedListener = OnFocusChangeListener { view, focused ->
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

    private fun initState() {
        // 사용자가 작성한 review
        editReviewViewModel.review.flowWithLifecycle(lifecycle)
            .onEach { setReviewEditor(it) }
            .launchIn(lifecycleScope)
        // EditReview Ui Event
        editReviewViewModel.editReviewUiEvent.flowWithLifecycle(lifecycle)
            .onEach {
                when (it) {
                    is EditReviewUiEvent.Fail.Exception -> showToast(it.message)
                    is EditReviewUiEvent.Fail.Message -> showToast(it.message)
                    EditReviewUiEvent.Loading -> {}
                    EditReviewUiEvent.Success -> finish()
                }
            }
            .launchIn(lifecycleScope)
    }

    /**
     * 사용자가 작성한 review 에 따라 View Binding
     * @param review viewModel 로 부터 전달된 리뷰 text
     */
    private fun setReviewEditor(review: String) {
        binding.tvTextCount.text = getString(
            R.string.edit_review_text_count,
            review.length,
            REVIEW_MAX_LENGTH
        )
        val btnColor = getColor(
            if (review.length in REVIEW_MIN_LENGTH..REVIEW_MAX_LENGTH) {
                com.peonlee.core.ui.R.color.brand100
            } else {
                com.peonlee.core.ui.R.color.brand50
            }
        )
        // 작성한 리뷰의 길이가 최소 <= length <= 최대인 경우 버튼 색상 변경
        binding.btnSave.backgroundTintList = ColorStateList.valueOf(btnColor)
    }

    /**
     * TODO 이후 공통 util 로 분리할 예정
     */
    fun Int.dpToPx(context: Context): Int {
        val density = context.resources.displayMetrics.density
        return (this * density).roundToInt()
    }
}
