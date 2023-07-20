package com.peonlee.data.login

import com.peonlee.data.Result
import com.peonlee.data.model.login.AuthRequest
import com.peonlee.data.model.login.AuthResult
import com.peonlee.data.model.request.ProductSearchRequest

interface LoginRepository {
    suspend fun login(token: String, loginRequest: AuthRequest) : Result<AuthResult>
    suspend fun signUp(token: String, loginRequest: AuthRequest) : Result<AuthResult>
}
