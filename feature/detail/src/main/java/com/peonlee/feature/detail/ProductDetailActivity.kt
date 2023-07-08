package com.peonlee.feature.detail

import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.feature.detail.databinding.ActivityProductDetailBinding

class ProductDetailActivity : BaseActivity<ActivityProductDetailBinding>() {
    private val adapter by lazy { ProductDetailListAdapter() }
    override fun bindingFactory(): ActivityProductDetailBinding = ActivityProductDetailBinding.inflate(layoutInflater)

    override fun initViews() {
        binding.rvProductDetail.adapter = adapter
        adapter.submitList(
            listOf(
                ProductDetailListItem.Product(
                    id = 0,
                    imageUrl = "https://cdn.pixabay.com/photo/2019/04/04/15/17/smartphone-4103051_1280.jpg",
                    productName = "Test",
                    price = 2000,
                    upvoteRate = 3,
                    reviewCount = 2,
                    eventList = listOf(
                        ProductDetailListItem.Event(imageUrl = "", title = "1+1"),
                        ProductDetailListItem.Event(imageUrl = "", title = "덤 증정"),
                        ProductDetailListItem.Event(imageUrl = "", title = "덤 증정")
                    )
                ),
                ProductDetailListItem.Divider(1),
                ProductDetailListItem.Rating(id = 2, rateCount = 5, upvoteRate = 60, downvoteRate = 40),
                ProductDetailListItem.Divider(3),
                ProductDetailListItem.NoneReview(id = 11),
                ProductDetailListItem.Divider(3),
                ProductDetailListItem.ReviewHeader(id = 4, reviewCount = 5),
                ProductDetailListItem.Review(id = 5, nickname = "사랑합니다.", writeDate = "", isUpvote = false, reviewText = "고갱님", isLike = false, likeCount = 0),
                ProductDetailListItem.Review(id = 6, nickname = "사랑합니다.", writeDate = "", isUpvote = true, reviewText = "고갱님", isLike = true, likeCount = 2),
                ProductDetailListItem.Review(id = 7, nickname = "사랑합니다.", writeDate = "", isUpvote = true, reviewText = "고갱님", isLike = true, likeCount = 4),
                ProductDetailListItem.Review(id = 8, nickname = "사랑합니다.", writeDate = "", isUpvote = false, reviewText = "고갱님", isLike = false, likeCount = 0),
                ProductDetailListItem.Review(id = 9, nickname = "사랑합니다.", writeDate = "", isUpvote = true, reviewText = "고갱님", isLike = true, likeCount = 5),
                ProductDetailListItem.Review(id = 10, nickname = "사랑합니다.", writeDate = "", isUpvote = false, reviewText = "고갱님", isLike = false, likeCount = 0)
            )
        )
    }
}
