package com.peonlee.data.review

import com.peonlee.data.model.review.SaveReviewRequest
import com.peonlee.data.setResult
import javax.inject.Inject

/**
 * 리뷰 관련 Repository
 */
class DefaultReviewRepository @Inject constructor(
    private val reviewApi: ReviewApi
) : ReviewRepository {
    /**
     * 새로운 리뷰 저장
     * TODO runCatching으로 Response 에러 대응 필요
     * @param productId 리뷰를 작성 하는 상품의 id
     * @param review 사용자가 작성한 리뷰
     * @return 리뷰 저장 API Response
     */
    override suspend fun saveReview(productId: Int, review: String) = setResult {
        reviewApi.saveReview(
            productId = productId,
            SaveReviewRequest(review = review)
        ).body() ?: throw IllegalArgumentException()
    }
}
