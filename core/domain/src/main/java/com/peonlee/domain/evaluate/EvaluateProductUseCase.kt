package com.peonlee.domain.evaluate

import com.peonlee.data.Result
import com.peonlee.data.model.Score
import com.peonlee.data.product.ProductRepository
import javax.inject.Inject

class EvaluateProductUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend fun likeProduct(productId: Int): Result<Score> {
        return productRepository.likeProduct(productId)
    }

    suspend fun dislikeProduct(productId: Int): Result<Score> {
        return productRepository.dislikeProduct(productId)
    }

    suspend fun undoProduct(productId: Int): Result<Score> {
        return productRepository.cancelLikeProduct(productId)
    }
}
