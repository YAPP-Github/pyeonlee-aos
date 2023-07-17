package com.peonlee.domain.login

import com.peonlee.data.Result
import com.peonlee.data.model.request.ProductSearchRequest
import com.peonlee.data.model.response.SearchProductResponse
import com.peonlee.data.product.ProductRepository
import com.peonlee.model.type.SortType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetHomeProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(
        orderBy: SortType,
        pageSize: Int = 10,
        retail: List<String>? = null,
        promotion: List<String>? = null
    ): Result<SearchProductResponse> = withContext(Dispatchers.IO) {
        productRepository.searchProduct(
            ProductSearchRequest(
                keyword = null,
                maxPrice = null,
                minPrice = null,
                offsetProductId = null,
                orderBy = orderBy.sortName,
                pageSize = pageSize,
                pbOnly = null,
                productCategoryTypeList = null,
                promotionRetailerList = retail,
                promotionTypeList = promotion
            )
        )
    }
}
