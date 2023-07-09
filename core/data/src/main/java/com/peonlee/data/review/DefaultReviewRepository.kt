package com.peonlee.data.review

import javax.inject.Inject

/**
 * 리뷰 관련 Repository
 */
class DefaultReviewRepository @Inject constructor(
) : ReviewRepository {
    /**
     * 새로운 리뷰 저장
     * @param productId 리뷰를 작성 하는 상품의 id
     * @param review 사용자가 작성한 리뷰
     */
    override suspend fun saveReview(productId: Int, review: String): Result<Unit> {
        TODO("Not yet implemented")
    }
}
