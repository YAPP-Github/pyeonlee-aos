package com.peonlee.data.model.request

data class ProductSearchRequest(
    val keyword: String? = null,
    val maxPrice: Int? = null,
    val minPrice: Int? = null,
    val offsetProductId: Int? = null,
    val orderBy: String? = null,
    val pageSize: Int = 10,
    val pbOnly: Boolean? = null,
    val productCategoryTypeList: List<String>? = null,
    val promotionRetailerList: List<String>? = null,
    val promotionTypeList: List<String>? = null
)
