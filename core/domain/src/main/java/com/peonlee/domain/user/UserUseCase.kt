package com.peonlee.domain.user

import com.peonlee.data.Result
import com.peonlee.data.user.UserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend fun deleteUser(memberId: Int): Result<Unit> = userRepository.deleteUser(memberId)
}
