package com.peonlee.data.product

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.peonlee.data.Result
import com.peonlee.data.model.Product
import com.peonlee.data.model.home.HomeInfoResponse
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
            offsetProductId = null,
            orderBy = searchRequest.orderBy,
            pageSize = 10,
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

    override fun getProductsPaging(productSearchRequest: ProductSearchRequest): Flow<PagingData<Product>> {
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

    /**
     * 홈 화면을 위한 모든 데이터 통합 요청
     * @return 홈 화면에 필요한 각 카테고리 데이터
     */
    override suspend fun getAllInfoForHome(): Result<HomeInfoResponse> = setResult {
        productApi.getAllInfoForHome()
    }
}
