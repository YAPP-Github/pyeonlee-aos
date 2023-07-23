package com.peonlee.data.comment

import com.peonlee.data.model.response.GetCommentResponse
import com.peonlee.data.model.review.SaveReviewRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CommentApi {
    @GET("v1/product/{productId}/comment")
    suspend fun getProductComments(
        @Path("productId") productId: Int,
        @Query("offsetProductCommentId") offsetProductCommentId: Int? = null,
        @Query("pageSize") pageSize: Int = 20
    ): GetCommentResponse

    @POST("v1/product/{productId}/comment/write")
    suspend fun saveComment(
        @Path("productId") productId: Int,
        @Body request: SaveReviewRequest
    )

    @POST("v1/product/{productId}/comment/edit")
    suspend fun editComment(
        @Path("productId") productId: Int,
        @Body request: SaveReviewRequest
    )

    @POST("v1/product/{productId}/comment/delete")
    suspend fun deleteComment(
        @Path("productId") productId: Int
    )

    @POST("v1/product/comment/{commentId}/like")
    suspend fun likeComment(
        @Path("commentId") commentId: Int
    )

    @POST("v1/product/comment/{commentId}/cancel")
    suspend fun unlikeComment(
        @Path("commentId") commentId: Int
    )
}
