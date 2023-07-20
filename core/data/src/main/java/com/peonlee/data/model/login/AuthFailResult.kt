package com.peonlee.data.model.login

import kotlinx.serialization.Serializable

@Serializable
data class AuthFailResult(
    val error: String,
    val path: String,
    val status: Int = 400,
    val timestamp: String
)
