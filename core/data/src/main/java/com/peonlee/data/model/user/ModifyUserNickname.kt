package com.peonlee.data.model.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ModifyUserNickname(
    @SerialName("nickname")
    val nickname: String
)
