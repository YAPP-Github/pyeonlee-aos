package com.peonlee.data.product

import com.peonlee.data.Result
import com.peonlee.data.model.ProductDetail
import com.peonlee.data.model.ProductSearch
import com.peonlee.data.model.request.ProductSearchRequest

interface ProductRepository {
    suspend fun getProductDetail(productId: Int): Result<ProductDetail>
    suspend fun searchProduct(searchRequest: ProductSearchRequest): Result<ProductSearch>

    suspend fun likeProduct(productId: Int): Result<Unit>
    suspend fun dislikeProduct(productId: Int): Result<Unit>
    suspend fun cancelLikeProduct(productId: Int): Result<Unit>
}
