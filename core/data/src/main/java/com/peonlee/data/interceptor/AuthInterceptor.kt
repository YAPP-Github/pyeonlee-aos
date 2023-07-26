package com.peonlee.data.interceptor

import com.peonlee.data.local.LocalDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

// https://stackoverflow.com/questions/71559755/accessing-datastore-preferences-inside-retrofit-client-hilt

class AuthInterceptor @Inject constructor(
    private val dataStore: LocalDataSource
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = runBlocking { dataStore.getAccessToken().first() }
        val authRequest = chain.request().newBuilder()
            .addHeader(
                "X-AUTH-TOKEN",
//                accessToken
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjg5NzcwNDk1LCJleHAiOjE3MjEzOTI4OTV9.xKIWAW5GWjmETFHwTppWK2bvXKWHAsgwxBpKKsXbxmA"
            )
            .build()

        println(authRequest)
        return chain.proceed(authRequest)
    }
}
