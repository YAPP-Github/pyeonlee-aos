package com.peonlee.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.peonlee.core.data.BuildConfig
import com.peonlee.data.di.NetworkModule.ConnectInfo.APPLICATION_JSON
import com.peonlee.data.di.NetworkModule.ConnectInfo.TIME_OUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            client(okHttpClient)
            baseUrl(BuildConfig.BASE_URL)
            addConverterFactory(Json.asConverterFactory(APPLICATION_JSON.toMediaType()))
        }.build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            val loggingInterceptor = HttpLoggingInterceptor()

            if (BuildConfig.DEBUG) {
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
            }

            addInterceptor(loggingInterceptor)
            connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            readTimeout(TIME_OUT, TimeUnit.SECONDS)
            writeTimeout(TIME_OUT, TimeUnit.SECONDS)
        }.build()
    }

    private object ConnectInfo {
        const val APPLICATION_JSON = "application/json"

        const val TIME_OUT = 60L
    }
}
