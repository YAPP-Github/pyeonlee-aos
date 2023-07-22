package com.peonlee.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val authRequest = chain.request().newBuilder()
            .addHeader(
                "X_ACCESS_TOKEN",
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjg5NzcwNDk1LCJleHAiOjE3MjEzOTI4OTV9.xKIWAW5GWjmETFHwTppWK2bvXKWHAsgwxBpKKsXbxmA"
            )
            .build()

        println(authRequest)
        return chain.proceed(authRequest)
    }
}
