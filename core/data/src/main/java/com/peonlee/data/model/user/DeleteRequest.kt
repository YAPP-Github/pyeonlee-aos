package com.peonlee.data.model.user

import kotlinx.serialization.Serializable

@Serializable
data class DeleteRequest(
    val memberId: Int
)
