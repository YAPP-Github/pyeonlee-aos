package com.peonlee.domain.login

import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    suspend fun login(token: String) {
        loginRepository.login(token)
    }
}
