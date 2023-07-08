package com.peonlee.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Promotion(
    val productId: Int,
    val promotionType: String,
    val retailerType: String
)
