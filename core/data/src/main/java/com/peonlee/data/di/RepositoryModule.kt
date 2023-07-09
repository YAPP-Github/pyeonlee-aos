package com.peonlee.data.di

import com.peonlee.data.login.LoginRepository
import com.peonlee.data.login.LoginRepositoryImpl
import com.peonlee.data.product.DefaultProductRepository
import com.peonlee.data.product.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindLoginRepository(
        loginRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        productRepository: DefaultProductRepository
    ): ProductRepository
}
