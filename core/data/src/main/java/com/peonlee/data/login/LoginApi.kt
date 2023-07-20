package com.peonlee.data.login

import com.peonlee.data.model.login.AuthRequest
import com.peonlee.data.model.login.AuthResult
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginApi {
    @POST("v1/member/signup")
    suspend fun signUp(
        @Header("X-AUTH-TOKEN") X_ACCESS_TOKEN: String,
        @Body signUpRequest: AuthRequest
    ): AuthResult

    @POST("v1/member/login")
    suspend fun login(
        @Header("X-AUTH-TOKEN") X_ACCESS_TOKEN: String,
        @Body loginRequest: AuthRequest
    ): AuthResult
}
