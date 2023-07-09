package com.peonlee.feature.detail

import android.content.Context
import android.content.Intent
import androidx.lifecycle.lifecycleScope
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.data.handle
import com.peonlee.data.product.ProductRepository
import com.peonlee.feature.detail.databinding.ActivityProductDetailBinding
import com.peonlee.review.edit.EditReviewActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProductDetailActivity : BaseActivity<ActivityProductDetailBinding>() {
    companion object {
        private const val DEFAULT_PRODUCT_ID = -1
        private const val EXTRA_PRODUCT_ID = "extra_product_id"
        fun startActivity(context: Context, productId: Int) {
            context.startActivity(Intent(context, ProductDetailActivity::class.java).apply {
                putExtra(EXTRA_PRODUCT_ID, productId)
            })
        }
    }

    @Inject
    lateinit var productRepository: ProductRepository

    private val productId by lazy { intent.getIntExtra(EXTRA_PRODUCT_ID, DEFAULT_PRODUCT_ID) }

    private val adapter by lazy {
        ProductDetailListAdapter {
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
            lifecycleScope.launch {
                productRepository.likeProduct(productId)
            }
        }
        binding.btnDownvote.setOnClickListener {
            lifecycleScope.launch {
                productRepository.dislikeProduct(productId)
            }
        }
        binding.btnReviewWrite.setOnClickListener {
            startActivity(Intent(this, EditReviewActivity::class.java))
        }
        binding.rvProductDetail.adapter = adapter
        lifecycleScope.launch {
            productRepository.getProductDetail(productId).handle({
                val itemList = listOf(
                    ProductDetailListItem.Product(
                        id = it.productId.toLong(),
                        imageUrl = it.imageUrl,
                        productName = it.name,
                        price = it.price,
                        upvoteRate = it.score.likeRatio,
                        reviewCount = 10,
                        eventList = it.promotionList.map { promotion ->
                            ProductDetailListItem.Event(
                                retailerType = enumValueOf(promotion.retailerType),
                                promotionType = enumValueOf(promotion.promotionType)
                            )
                        }
                    ),
                    ProductDetailListItem.Divider(1),
                    ProductDetailListItem.Rating(
                        id = 2,
                        rateCount = it.score.totalCount,
                        upvoteRate = it.score.likeRatio,
                        downvoteRate = 100 - it.score.likeRatio
                    ),
                    ProductDetailListItem.Divider(3),
                    ProductDetailListItem.NoneReview(id = 11),
                    ProductDetailListItem.Divider(3),
                    ProductDetailListItem.ReviewHeader(id = 4, reviewCount = 5),
                    ProductDetailListItem.Review(
                        id = 5,
                        nickname = "사랑합니다.",
                        writeDate = "",
                        isUpvote = false,
                        reviewText = "고갱님",
                        isLike = false,
                        likeCount = 1,
                        isMine = false
                    ),
                    ProductDetailListItem.Review(
                        id = 6,
                        nickname = "사랑합니다.",
                        writeDate = "",
                        isUpvote = true,
                        reviewText = "고갱님",
                        isLike = true,
                        likeCount = 2,
                        isMine = true
                    ),
                    ProductDetailListItem.Review(
                        id = 7,
                        nickname = "사랑합니다.",
                        writeDate = "",
                        isUpvote = true,
                        reviewText = "고갱님",
                        isLike = true,
                        likeCount = 4,
                        isMine = false
                    ),
                    ProductDetailListItem.Review(
                        id = 8,
                        nickname = "사랑합니다.",
                        writeDate = "",
                        isUpvote = false,
                        reviewText = "고갱님",
                        isLike = false,
                        likeCount = 0,
                        isMine = true
                    ),
                    ProductDetailListItem.Review(
                        id = 9,
                        nickname = "사랑합니다.",
                        writeDate = "",
                        isUpvote = true,
                        reviewText = "고갱님",
                        isLike = true,
                        likeCount = 5,
                        isMine = false
                    ),
                    ProductDetailListItem.Review(
                        id = 10,
                        nickname = "사랑합니다.",
                        writeDate = "",
                        isUpvote = false,
                        reviewText = "고갱님",
                        isLike = false,
                        likeCount = 2,
                        isMine = false
                    )
                )
                adapter.submitList(itemList)
            })
        }
    }
}
