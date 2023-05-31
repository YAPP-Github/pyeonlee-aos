package com.peonlee.domain.login

interface LoginRepository {
    suspend fun login(token: String)
}
