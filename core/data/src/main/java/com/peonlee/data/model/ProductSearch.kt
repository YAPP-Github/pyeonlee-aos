package com.peonlee.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductSearch(
    @SerialName("content")
    val content: List<Content>,
    @SerialName("lastId")
    val lastId: Int = 1
)
