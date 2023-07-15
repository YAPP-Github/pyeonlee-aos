package com.peonlee.feature.detail

import android.content.Context
import android.content.Intent
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.peonlee.core.ui.Navigator
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.data.handle
import com.peonlee.data.model.ProductRatingType
import com.peonlee.data.model.Score
import com.peonlee.data.product.ProductRepository
import com.peonlee.feature.detail.databinding.ActivityProductDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
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

    private val productId by lazy { intent.getIntExtra(EXTRA_PRODUCT_ID, DEFAULT_PRODUCT_ID) }

    private val adapter by lazy {
        ProductDetailListAdapter(navigator) {
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
                productRepository.likeProduct(productId).handle({
                    handleVoteState(ProductRatingType.LIKE)
                    updateScore(it)
                })
            }
        }
        binding.btnDownvote.setOnClickListener {
            lifecycleScope.launch {
                productRepository.dislikeProduct(productId).handle({
                    handleVoteState(ProductRatingType.DISLIKE)
                    updateScore(it)
                })
            }
        }
        binding.btnCancel.setOnClickListener {
            lifecycleScope.launch {
                productRepository.cancelLikeProduct(productId).handle({
                    handleVoteState(ProductRatingType.NONE)
                    updateScore(it)
                })
            }
        }
        binding.btnReviewWrite.setOnClickListener {
            navigator.navigateToEditReview(this)
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
                    ProductDetailListItem.Score(
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

                binding.llVoteContainer.isVisible = true
                handleVoteState(ProductRatingType.NONE)
            })
        }
    }

    private fun handleVoteState(voteType: ProductRatingType) {
        binding.llAlreadyVoteContainer.isVisible = voteType != ProductRatingType.NONE
        binding.llUpvote.isVisible = voteType == ProductRatingType.LIKE
        binding.llDownvote.isVisible = voteType == ProductRatingType.DISLIKE
        binding.llNoneVoteContainer.isVisible = voteType == ProductRatingType.NONE
    }

    private fun updateScore(score: Score) {
        val newList = adapter.currentList.toMutableList()
        val detailItemIndex = newList.indexOfFirst { it is ProductDetailListItem.Product }
        newList[detailItemIndex] = (newList[detailItemIndex] as ProductDetailListItem.Product).copy(upvoteRate = score.likeRatio)

        val scoreItemIndex = newList.indexOfFirst { it is ProductDetailListItem.Score }
        newList[scoreItemIndex] = (newList[scoreItemIndex] as ProductDetailListItem.Score).copy(
            rateCount = score.totalCount,
            upvoteRate = score.likeRatio,
            downvoteRate = 100 - score.likeRatio
        )
        adapter.submitList(newList)
    }
}
