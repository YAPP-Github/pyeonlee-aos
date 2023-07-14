package com.peonlee.data.evaluate

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.peonlee.data.model.Content
import com.peonlee.data.product.ProductApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EvaluateRepository @Inject constructor(private val productApi: ProductApi) {
    fun getSearchProduct(): Flow<PagingData<Content>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { EvaluatePagingSource(productApi) }
        ).flow
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}
