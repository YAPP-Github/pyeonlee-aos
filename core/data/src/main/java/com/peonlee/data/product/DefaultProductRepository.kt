package com.peonlee.data.product

import com.peonlee.data.model.request.ProductSearchRequest
import com.peonlee.data.setResult
import javax.inject.Inject

class DefaultProductRepository @Inject constructor(
    private val productApi: ProductApi
) : ProductRepository {
    override suspend fun getProductDetail(productId: Int) = setResult {
        productApi.getProductDetail(productId)
    }

    override suspend fun searchProduct(searchRequest: ProductSearchRequest) = setResult {
        productApi.searchProduct(searchRequest)
    }
}
