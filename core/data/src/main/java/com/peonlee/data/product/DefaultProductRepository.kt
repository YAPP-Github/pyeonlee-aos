package com.peonlee.data.product

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.peonlee.data.model.Content
import com.peonlee.data.model.request.ProductSearchRequest
import com.peonlee.data.setResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultProductRepository @Inject constructor(
    private val productApi: ProductApi
) : ProductRepository {
    override suspend fun getProductDetail(productId: Int) = setResult {
        productApi.getProductDetail(productId)
    }

    override suspend fun searchProduct(searchRequest: ProductSearchRequest) = setResult {
        productApi.searchProduct(
            keyword = searchRequest.keyword,
            maxPrice = searchRequest.maxPrice,
            minPrice = searchRequest.minPrice,
            offsetProductId = 1,
            orderBy = searchRequest.orderBy,
            pageSize =  10,
            pbOnly = searchRequest.pbOnly,
            productCategoryTypeList = searchRequest.productCategoryTypeList,
            promotionRetailerList = searchRequest.promotionRetailerList,
            promotionTypeList = searchRequest.promotionTypeList
        )
    }

    override suspend fun likeProduct(productId: Int) = setResult {
        productApi.likeProduct(productId)
    }

    override suspend fun dislikeProduct(productId: Int) = setResult {
        productApi.dislikeProduct(productId)
    }

    override suspend fun cancelLikeProduct(productId: Int) = setResult {
        productApi.cancelLikeProductDetail(productId)
    }

    override fun getProductsPaging(productSearchRequest: ProductSearchRequest): Flow<PagingData<Content>> {
        return Pager(
            config = PagingConfig(
                pageSize = ProductPagingSource.PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ProductPagingSource(productSearchRequest, productApi)
            }
        ).flow
    }
}
