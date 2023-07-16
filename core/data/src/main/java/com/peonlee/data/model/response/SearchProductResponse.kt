package com.peonlee.data.model.response

import com.peonlee.data.model.Product
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchProductResponse(
    @SerialName("content")
    val content: List<Product>,
    @SerialName("lastId")
    val lastId: Int = 1
)
