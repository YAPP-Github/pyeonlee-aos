package com.peonlee.domain.login

import com.peonlee.data.login.LoginRepository
import com.peonlee.data.model.login.AuthRequest
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend fun login(
        token: String,
        loginRequest: AuthRequest
    ) {
        loginRepository.login(token, loginRequest)
    }

    suspend fun signUp(
        token: String,
        loginRequest: AuthRequest
    ) {
        loginRepository.login(token, loginRequest)
    }
}
