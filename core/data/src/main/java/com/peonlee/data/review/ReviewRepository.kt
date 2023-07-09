package com.peonlee.data.review

/**
 * 리뷰와 관련된 Repository
 */
interface ReviewRepository {
    /**
     * 새로운 리뷰 저장
     * @param productId 리뷰를 작성 하는 상품의 id
     * @param review 사용자가 작성한 리뷰
     */
    suspend fun saveReview(productId: Int, review: String): Result<Unit>
}
