package com.peonlee.data.login

import com.peonlee.data.Result
import com.peonlee.data.model.login.AuthRequest
import com.peonlee.data.model.login.AuthResult
import com.peonlee.data.setResult
import javax.inject.Inject

class DefaultLoginRepository @Inject constructor(private val loginApi: LoginApi) : LoginRepository {
    override suspend fun login(token: String, loginRequest: AuthRequest): Result<AuthResult> {
        return setResult { loginApi.login(token, loginRequest) }
    }
    override suspend fun signUp(token: String, loginRequest: AuthRequest): Result<AuthResult> {
        return setResult { loginApi.login(token, loginRequest) }
    }
}
