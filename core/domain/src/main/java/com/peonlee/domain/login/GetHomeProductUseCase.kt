package com.peonlee.domain.login

import com.peonlee.data.Result
import com.peonlee.data.model.home.HomeInfoResponse
import com.peonlee.data.product.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetHomeProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): Result<HomeInfoResponse> = withContext(Dispatchers.IO) {
        productRepository.getAllInfoForHome()
    }
}
