package com.peonlee.feature.detail

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.peonlee.core.ui.Navigator
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.data.model.ProductRatingType
import com.peonlee.data.product.ProductRepository
import com.peonlee.feature.detail.databinding.ActivityProductDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class ProductDetailActivity : BaseActivity<ActivityProductDetailBinding>() {
    companion object {
        private const val DEFAULT_PRODUCT_ID = -1
        private const val EXTRA_PRODUCT_ID = "extra_product_id"
        fun startActivity(context: Context, productId: Int) {
            context.startActivity(
                Intent(context, ProductDetailActivity::class.java).apply {
                    putExtra(EXTRA_PRODUCT_ID, productId)
                }
            )
        }
    }

    @Inject
    lateinit var productRepository: ProductRepository

    @Inject
    lateinit var navigator: Navigator

    private val viewModel: ProductDetailViewModel by viewModels()

    private val productId by lazy { intent.getIntExtra(EXTRA_PRODUCT_ID, DEFAULT_PRODUCT_ID) }

    private val adapter by lazy {
        ProductDetailListAdapter({
            with(viewModel.productDetail) {
                navigator.navigateToEditReview(this@ProductDetailActivity, productId, imageUrl, name, price)
            }
        }) {
            ReviewManageDialog().run {
                show(supportFragmentManager, tag)
            }
        }
    }

    override fun bindingFactory(): ActivityProductDetailBinding = ActivityProductDetailBinding.inflate(layoutInflater)

    override fun initViews() {
        if (productId == DEFAULT_PRODUCT_ID) {
            finish()
            return
        }
        binding.ivBackBtn.setOnClickListener {
            finish()
        }
        binding.btnUpvote.setOnClickListener {
            viewModel.likeProduct(productId)
        }
        binding.btnDownvote.setOnClickListener {
            viewModel.dislikeProduct(productId)
        }
        binding.btnCancel.setOnClickListener {
            viewModel.cancelLikeProduct(productId)
        }
        binding.btnReviewWrite.setOnClickListener {
            with(viewModel.productDetail) {
                navigator.navigateToEditReview(this@ProductDetailActivity, productId, imageUrl, name, price)
            }
        }

        binding.rvProductDetail.adapter = adapter
        viewModel.productDetailItemList.flowWithLifecycle(
            lifecycle
        ).onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)

        viewModel.productRatingType.flowWithLifecycle(
            lifecycle
        ).onEach {
            binding.llVoteContainer.isVisible = true
            handleVoteState(it)
        }.launchIn(lifecycleScope)

        viewModel.start(productId)
    }

    private fun handleVoteState(voteType: ProductRatingType) {
        binding.llAlreadyVoteContainer.isVisible = voteType != ProductRatingType.NONE
        binding.llUpvote.isVisible = voteType == ProductRatingType.LIKE
        binding.llDownvote.isVisible = voteType == ProductRatingType.DISLIKE
        binding.llNoneVoteContainer.isVisible = voteType == ProductRatingType.NONE
    }
}
