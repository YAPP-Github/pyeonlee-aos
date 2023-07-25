package com.peonlee.domain.user

import com.peonlee.data.Result
import com.peonlee.data.model.user.DeleteRequest
import com.peonlee.data.user.UserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend fun deleteUser(deleteRequest: DeleteRequest): Result<Unit> = userRepository.deleteUser(deleteRequest)
}
