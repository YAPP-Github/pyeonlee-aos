package com.peonlee.data.model.login

import kotlinx.serialization.Serializable

@Serializable
data class AuthResult(
    val accessToken: String
)
