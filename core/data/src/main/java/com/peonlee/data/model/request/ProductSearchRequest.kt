package com.peonlee.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductSearchRequest(
    @SerialName("keyword")
    val keyword: String? = null,
    @SerialName("maxPrice")
    val maxPrice: Int? = null,
    @SerialName("minPrice")
    val minPrice: Int? = null,
    @SerialName("offsetProductId")
    val offsetProductId: Int? = null,
    @SerialName("orderBy")
    val orderBy: String? = null,
    @SerialName("pageSize")
    val pageSize: Int = 10,
    @SerialName("pbOnly")
    val pbOnly: Boolean? = null,
    @SerialName("productCategoryTypeList")
    val productCategoryTypeList: List<String>? = null,
    @SerialName("promotionRetailerList")
    val promotionRetailerList: List<String>? = null,
    @SerialName("promotionTypeList")
    val promotionTypeList: List<String>? = null
)
