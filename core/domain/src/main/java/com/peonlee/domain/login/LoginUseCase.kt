package com.peonlee.domain.login

import com.peonlee.data.Result
import com.peonlee.data.login.LoginRepository
import com.peonlee.data.model.login.AuthRequest
import com.peonlee.data.model.login.AuthResult
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    suspend fun login(
        token: String,
        loginRequest: AuthRequest
    ): Result<AuthResult> = loginRepository.login(token, loginRequest)

    suspend fun signUp(
        token: String,
        loginRequest: AuthRequest
    ): Result<AuthResult> = loginRepository.signUp(token, loginRequest)
}
