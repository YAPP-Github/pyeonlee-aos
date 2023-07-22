package com.peonlee.data.evaluate

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.peonlee.common.exception.NoneDataException
import com.peonlee.data.model.Product
import com.peonlee.data.product.ProductApi
import javax.inject.Inject

class EvaluatePagingSource @Inject constructor(private val productApi: ProductApi) : PagingSource<Int, Product>() {
    private var offsetProductId: Int? = null

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

            val productsResponse = productApi.searchProductTemp(offsetProductId = offsetId)

            if (productsResponse.isSuccessful && productsResponse.body() != null) {
                val products = productsResponse.body()!!
                val lastId = products.lastId

                LoadResult.Page(
                    data = products.content,
                    prevKey = offsetId,
                    nextKey = if (offsetId == lastId) null else lastId
                )
            } else {
                throw NoneDataException()
            }
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }

    }

    companion object {
        const val PAGE_SIZE = 20
    }
}
