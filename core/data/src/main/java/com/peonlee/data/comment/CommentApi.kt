package com.peonlee.data.comment

import com.peonlee.data.model.response.GetCommentResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CommentApi {
    @GET("v1/product/{productId}/comment")
    suspend fun getProductComments(
        @Path("productId") productId: Int,
        @Query("offsetProductCommentId") offsetProductCommentId: Int? = null,
        @Query("pageSize") pageSize: Int = 20
    ): GetCommentResponse
}
