package com.peonlee.data.user

import com.peonlee.data.model.user.UserResponse
import retrofit2.http.GET

/**
 * 사용자 관련 API
 */
interface UserApi {
    /**
     * 자신의 정보를 요청
     */
    @GET("v1/member/summary")
    suspend fun getUserInfo(): UserResponse
}
