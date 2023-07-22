package com.peonlee.data.di

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.peonlee.core.data.BuildConfig
import com.peonlee.data.comment.CommentApi
import com.peonlee.data.di.NetworkModule.ConnectInfo.APPLICATION_JSON
import com.peonlee.data.di.NetworkModule.ConnectInfo.TIME_OUT
import com.peonlee.data.interceptor.AuthInterceptor
import com.peonlee.data.login.LoginApi
import com.peonlee.data.product.ProductApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
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
    fun provideJson(): Json {
        return Json {
            coerceInputValues = true
            ignoreUnknownKeys = true
        }
    }

    @Singleton
    @Provides
    fun provideRetrofit(json: Json, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(json.asConverterFactory(APPLICATION_JSON.toMediaType()))
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        val json = Json { prettyPrint = true }
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                if (!message.startsWith("{") && !message.startsWith("[")) {
                    Log.d("OkHttp", message)
                    return
                }
                try {
                    Log.d("OkHttp", json.encodeToString((Json.parseToJsonElement(message).jsonObject)))
                } catch (m: Exception) {
                    Log.d("OkHttp", message)
                }
            }
        }).apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

        return OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideProductApi(retrofit: Retrofit): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCommentApi(retrofit: Retrofit): CommentApi {
        return retrofit.create(CommentApi::class.java)
    }

    @Singleton
    @Provides
    fun provideLoginApi(retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    private object ConnectInfo {
        const val APPLICATION_JSON = "application/json"

        const val TIME_OUT = 30000L
    }
}
