package com.peonlee.domain.login

import com.peonlee.data.login.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend fun login(token: String) {
        loginRepository.login(token)
    }
}
