package com.peonlee.data.evaluate

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.peonlee.data.model.Product
import com.peonlee.data.product.ProductApi

class EvaluatePagingSource(private val productApi: ProductApi) : PagingSource<Int, Product>() {

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val closestPageToPosition = state.closestPageToPosition(anchorPosition)
            closestPageToPosition?.prevKey?.plus(PAGE_SIZE)
                ?: closestPageToPosition?.nextKey?.minus(PAGE_SIZE)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val offsetId = params.key
            val products = productApi.evaluateProduct(
                offsetProductId = offsetId,
                pageSize = PAGE_SIZE
            )

            LoadResult.Page(
                data = products.content,
                prevKey = offsetId,
                nextKey = if (offsetId == products.lastId) null else products.lastId
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    companion object {
        const val PAGE_SIZE = 20
    }
}
