package com.peonlee.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductSearch(
    val content: List<Content>,
    val lastId: Int
)
