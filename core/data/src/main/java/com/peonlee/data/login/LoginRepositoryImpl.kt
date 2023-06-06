package com.peonlee.data.login

import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor() : LoginRepository {
    override suspend fun login(token: String) {
        // TODO Type 정해지고 연동
    }
}
