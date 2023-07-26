package com.peonlee.data.user

import com.peonlee.data.model.user.ModifyUserNickname
import com.peonlee.data.model.user.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * 사용자 관련 API
 */
interface UserApi {
    /**
     * 자신의 정보를 요청
     */
    @GET("v1/member/summary")
    suspend fun getUserInfo(): UserResponse

    /**
     * 닉네임을 변경
     */
    @POST("v1/member/nickname")
    suspend fun changeUserNickname(
        @Body modifyUserNickname: ModifyUserNickname
    )
}
