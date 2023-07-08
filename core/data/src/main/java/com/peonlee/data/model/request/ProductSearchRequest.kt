package com.peonlee.data.model.request

data class ProductSearchRequest(
    val keyword: String?,
    val maxPrice: Int?,
    val minPrice: Int?,
    val offsetProductId: Int?,
    val orderBy: String?,
    val pageSize: Int,
    val pbOnly: Boolean?,
    val productCategoryTypeList: List<String>?,
    val promotionRetailerList: List<String>?,
    val promotionTypeList: List<String>?
)
