package com.peonlee.data.evaluate

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.peonlee.data.model.Content
import com.peonlee.data.product.ProductApi
import javax.inject.Inject

class EvaluatePagingSource @Inject constructor(private val productApi: ProductApi) : PagingSource<Int, Content>() {
    private var offsetProductId: Int? = null

    override fun getRefreshKey(state: PagingState<Int, Content>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val closestPageToPosition = state.closestPageToPosition(anchorPosition)
            closestPageToPosition?.prevKey?.plus(PAGE_SIZE)
                ?: closestPageToPosition?.nextKey?.minus(PAGE_SIZE)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Content> {
        val page = params.key ?: 1
        val productList = productApi.searchProductTemp(offsetProductId = offsetProductId).copy()

        offsetProductId = productList.lastId

        return LoadResult.Page(
            data = productList.content,
            prevKey = when (page) {
                1 -> null
                else -> page - 1
            },
            nextKey = offsetProductId?.let { page + 1 }
        )
    }

    companion object {
        const val PAGE_SIZE = 20
    }
}
