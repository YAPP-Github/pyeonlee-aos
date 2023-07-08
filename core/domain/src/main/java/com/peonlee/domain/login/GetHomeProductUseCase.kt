package com.peonlee.domain.login

import com.peonlee.data.Result
import com.peonlee.data.model.ProductSearch
import com.peonlee.data.model.request.ProductSearchRequest
import com.peonlee.data.product.ProductRepository
import com.peonlee.model.type.SortType
import javax.inject.Inject

class GetHomeProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(
        orderBy: SortType,
        retail: List<String>? = null,
        promotion: List<String>? = null
    ): Result<ProductSearch> {
        return productRepository.searchProduct(
            ProductSearchRequest(
                keyword = null,
                maxPrice = null,
                minPrice = null,
                offsetProductId = null,
                orderBy = orderBy.sortName,
                pageSize = 10,
                pbOnly = null,
                productCategoryTypeList = null,
                promotionRetailerList = retail,
                promotionTypeList = promotion
            )
        )
    }
}
