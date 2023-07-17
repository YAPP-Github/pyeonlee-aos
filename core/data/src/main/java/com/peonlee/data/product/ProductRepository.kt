package com.peonlee.data.product

import androidx.paging.PagingData
import com.peonlee.data.Result
import com.peonlee.data.model.Product
import com.peonlee.data.model.ProductDetail
import com.peonlee.data.model.Score
import com.peonlee.data.model.request.ProductSearchRequest
import com.peonlee.data.model.response.SearchProductResponse
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProductDetail(productId: Int): Result<ProductDetail>
    suspend fun searchProduct(searchRequest: ProductSearchRequest): Result<SearchProductResponse>

    suspend fun likeProduct(productId: Int): Result<Score>
    suspend fun dislikeProduct(productId: Int): Result<Score>
    suspend fun cancelLikeProduct(productId: Int): Result<Score>

    // 상품 paging 요청
    fun getProductsPaging(productSearchRequest: ProductSearchRequest): Flow<PagingData<Product>>
}
