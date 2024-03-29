package com.peonlee.data.product

import com.peonlee.data.model.ProductDetail
import com.peonlee.data.model.Score
import com.peonlee.data.model.home.HomeInfoResponse
import com.peonlee.data.model.response.SearchProductResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("v1/product/{productId}/detail")
    suspend fun getProductDetail(
        @Path("productId") productId: Int
    ): ProductDetail

    @POST("v1/product/{productId}/rate/like")
    suspend fun likeProduct(
        @Path("productId") productId: Int
    ): Score

    @POST("v1/product/{productId}/rate/dislike")
    suspend fun dislikeProduct(
        @Path("productId") productId: Int
    ): Score

    @POST("v1/product/{productId}/rate/cancel")
    suspend fun cancelLikeProductDetail(
        @Path("productId") productId: Int
    ): Score

    @GET("v1/product/search")
    suspend fun searchProduct(
        @Query("keyword") keyword: String? = null,
        @Query("maxPrice") maxPrice: Int? = null,
        @Query("minPrice") minPrice: Int? = null,
        @Query("offsetProductId") offsetProductId: Int? = null,
        @Query("orderBy") orderBy: String? = null,
        @Query("pageSize") pageSize: Int? = null,
        @Query("pbOnly") pbOnly: Boolean? = null,
        @Query("productCategoryTypeList") productCategoryTypeList: List<String>? = null,
        @Query("promotionRetailerList") promotionRetailerList: List<String>? = null,
        @Query("promotionTypeList") promotionTypeList: List<String>? = null
    ): SearchProductResponse

    @GET("v1/product/unrated")
    suspend fun evaluateProduct(
        @Query("pageSize") pageSize: Int,
        @Query("offsetProductId") offsetProductId: Int?
    ): SearchProductResponse

    /**
     * [GET] v1/home
     */
    @GET("v1/home")
    suspend fun getAllInfoForHome(): HomeInfoResponse
}
