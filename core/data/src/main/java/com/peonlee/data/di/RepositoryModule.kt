package com.peonlee.data.di

import com.peonlee.data.comment.CommentRepository
import com.peonlee.data.comment.DefaultCommentRepository
import com.peonlee.data.login.DefaultLoginRepository
import com.peonlee.data.login.LoginRepository
import com.peonlee.data.product.DefaultProductRepository
import com.peonlee.data.product.ProductRepository
import com.peonlee.data.review.DefaultReviewRepository
import com.peonlee.data.review.ReviewRepository
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
        loginRepository: DefaultLoginRepository
    ): LoginRepository

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        productRepository: DefaultProductRepository
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindReviewRepository(
        reviewRepository: DefaultReviewRepository
    ): ReviewRepository

    @Binds
    @Singleton
    abstract fun bindCommentRepository(
        repository: DefaultCommentRepository
    ): CommentRepository
}
