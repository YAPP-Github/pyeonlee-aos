package com.peonlee.data.product

import com.peonlee.data.model.ProductDetail
import com.peonlee.data.model.ProductSearch
import com.peonlee.data.model.request.ProductSearchRequest
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("v1/product/{productId}/detail")
    suspend fun getProductDetail(
        @Path("productId") productId: Int
    ): ProductDetail

    @GET("v1/product/search")
    suspend fun searchProduct(
        @Query("query") request: ProductSearchRequest
    ): ProductSearch
}
