package com.peonlee.data.di

import com.peonlee.data.login.LoginRepository
import com.peonlee.data.login.LoginRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LoginModule {
    @Binds
    @Singleton
    fun bindLoginRepository(
        loginRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository {
        return loginRepositoryImpl
    }
}
