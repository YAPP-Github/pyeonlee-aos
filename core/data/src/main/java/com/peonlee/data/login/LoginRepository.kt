package com.peonlee.data.login

interface LoginRepository {
    suspend fun login(token: String)
}
