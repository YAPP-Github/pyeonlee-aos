package com.peonlee.data.user

import com.peonlee.data.Result
import com.peonlee.data.model.user.DeleteRequest
import com.peonlee.data.model.user.UserResponse

/**
 * 사용자와 관련된 Repository
 */
interface UserRepository {
    /**
     * 사용자 정보 요청
     */
    suspend fun getUserInfo(): Result<UserResponse>
    suspend fun deleteUser(deleteRequest: DeleteRequest): Result<Unit>

    /**
     * 사용자 닉네임 변경
     */
    suspend fun changeUserNickname(nickname: String): Result<Unit>
}
