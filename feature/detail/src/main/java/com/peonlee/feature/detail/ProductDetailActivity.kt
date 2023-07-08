package com.peonlee.feature.detail

import androidx.lifecycle.lifecycleScope
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.data.handle
import com.peonlee.data.product.ProductRepository
import com.peonlee.feature.detail.databinding.ActivityProductDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProductDetailActivity : BaseActivity<ActivityProductDetailBinding>() {
    @Inject
    lateinit var productRepository: ProductRepository

    private val adapter by lazy { ProductDetailListAdapter() }
    override fun bindingFactory(): ActivityProductDetailBinding = ActivityProductDetailBinding.inflate(layoutInflater)

    override fun initViews() {
        binding.ivBackBtn.setOnClickListener {
            finish()
        }
        binding.rvProductDetail.adapter = adapter
        lifecycleScope.launch {
            productRepository.getProductDetail(721).handle({
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
                        likeCount = 0
                    ),
                    ProductDetailListItem.Review(
                        id = 6,
                        nickname = "사랑합니다.",
                        writeDate = "",
                        isUpvote = true,
                        reviewText = "고갱님",
                        isLike = true,
                        likeCount = 2
                    ),
                    ProductDetailListItem.Review(
                        id = 7,
                        nickname = "사랑합니다.",
                        writeDate = "",
                        isUpvote = true,
                        reviewText = "고갱님",
                        isLike = true,
                        likeCount = 4
                    ),
                    ProductDetailListItem.Review(
                        id = 8,
                        nickname = "사랑합니다.",
                        writeDate = "",
                        isUpvote = false,
                        reviewText = "고갱님",
                        isLike = false,
                        likeCount = 0
                    ),
                    ProductDetailListItem.Review(
                        id = 9,
                        nickname = "사랑합니다.",
                        writeDate = "",
                        isUpvote = true,
                        reviewText = "고갱님",
                        isLike = true,
                        likeCount = 5
                    ),
                    ProductDetailListItem.Review(
                        id = 10,
                        nickname = "사랑합니다.",
                        writeDate = "",
                        isUpvote = false,
                        reviewText = "고갱님",
                        isLike = false,
                        likeCount = 0
                    )
                )
                adapter.submitList(itemList)
            })
        }
    }
}
