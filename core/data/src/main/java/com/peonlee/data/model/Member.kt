package com.peonlee.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Member(
    val memberId: Int,
    val nickname: String
)
