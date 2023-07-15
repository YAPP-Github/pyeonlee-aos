package com.peonlee.data.review

import com.peonlee.data.model.review.SaveReviewRequest
import com.peonlee.data.model.review.SaveReviewResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * 리뷰와 관련된 API 관리
 */
interface ReviewApi {
    /**
     * 리뷰 추가 요청
     * @param productId 리뷰를 작성할 상품의 id
     * @param content 사용자가 작성한 리뷰 내용
     */
    @POST("v1/product/{productId}/comment/write")
    suspend fun saveReview(
        @Path("productId") productId: Int,
        @Body request: SaveReviewRequest
    ): Response<SaveReviewResponse>

    @POST("v1/product/{productId}/comment/edit")
    suspend fun editReview(
        @Path("productId") productId: Int,
        @Body request: SaveReviewRequest
    ): Response<SaveReviewResponse>

    @POST("v1/product/{productId}/comment/delete")
    suspend fun deleteReview(
        @Path("productId") productId: Int
    ): Response<Unit>
}
