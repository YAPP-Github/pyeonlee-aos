package com.peonlee.data.user

import com.peonlee.data.Result
import com.peonlee.data.model.user.DeleteRequest
import com.peonlee.data.model.user.UserResponse
import com.peonlee.data.setResult
import javax.inject.Inject

class DefaultUserRepository @Inject constructor(
    private val userApi: UserApi
) : UserRepository {
    override suspend fun getUserInfo(): Result<UserResponse> = setResult {
        userApi.getUserInfo()
    }

    override suspend fun deleteUser(deleteRequest: DeleteRequest): Result<Unit> = setResult {
        userApi.deleteUser(deleteRequest)
    }
}
