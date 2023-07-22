package com.peonlee.data.model.login

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    val idToken: String,
    val loginType: String
)
