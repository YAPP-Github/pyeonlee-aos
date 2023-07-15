package com.peonlee.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peonlee.data.handle
import com.peonlee.data.model.ProductDetail
import com.peonlee.data.model.ProductRatingType
import com.peonlee.data.model.Score
import com.peonlee.data.product.ProductRepository
import com.peonlee.data.review.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val reviewRepository: ReviewRepository
) : ViewModel() {
    lateinit var productDetail: ProductDetail
        private set

    private val _productRatingType = MutableSharedFlow<ProductRatingType>()
    val productRatingType: SharedFlow<ProductRatingType> = _productRatingType.asSharedFlow()

    private val _productDetailItemList = MutableStateFlow<List<ProductDetailListItem>>(emptyList())
    val productDetailItemList: StateFlow<List<ProductDetailListItem>> = _productDetailItemList.asStateFlow()

    fun start(productId: Int) {
        viewModelScope.launch {
            productRepository.getProductDetail(productId).handle({
                productDetail = it
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
                _productDetailItemList.value = itemList
                _productRatingType.emit(it.productRatingType)
            })
        }
    }

    fun likeProduct(productId: Int) {
        viewModelScope.launch {
            productRepository.likeProduct(productId).handle({
                _productRatingType.emit(ProductRatingType.LIKE)
                updateScore(it)
            })
        }
    }

    fun dislikeProduct(productId: Int) {
        viewModelScope.launch {
            productRepository.dislikeProduct(productId).handle({
                _productRatingType.emit(ProductRatingType.DISLIKE)
                updateScore(it)
            })
        }
    }

    fun cancelLikeProduct(productId: Int) {
        viewModelScope.launch {
            productRepository.cancelLikeProduct(productId).handle({
                _productRatingType.emit(ProductRatingType.NONE)
                updateScore(it)
            })
        }
    }

    private fun updateScore(score: Score) {
        val newList = _productDetailItemList.value.toMutableList()
        val detailItemIndex = newList.indexOfFirst { it is ProductDetailListItem.Product }
        newList[detailItemIndex] = (newList[detailItemIndex] as ProductDetailListItem.Product).copy(upvoteRate = score.likeRatio)

        val scoreItemIndex = newList.indexOfFirst { it is ProductDetailListItem.Score }
        newList[scoreItemIndex] = (newList[scoreItemIndex] as ProductDetailListItem.Score).copy(
            rateCount = score.totalCount,
            upvoteRate = score.likeRatio,
            downvoteRate = 100 - score.likeRatio
        )
        _productDetailItemList.value = newList
    }

    fun deleteReview(productId: Int) {
        viewModelScope.launch {
            reviewRepository.deleteReview(productId).handle({
                // TODO remove review Item
            })
        }
    }
}
