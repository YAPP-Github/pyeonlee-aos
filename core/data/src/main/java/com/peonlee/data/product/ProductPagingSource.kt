package com.peonlee.data.product

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.peonlee.data.model.Content
import com.peonlee.data.model.request.ProductSearchRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductPagingSource(
    private val productSearchRequest: ProductSearchRequest,
    private val productApi: ProductApi
) : PagingSource<Int, Content>() {
    override fun getRefreshKey(state: PagingState<Int, Content>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val closetPageToPosition = state.closestPageToPosition(anchorPosition)
            closetPageToPosition?.prevKey?.plus(PAGE_SIZE)
                ?: closetPageToPosition?.nextKey?.minus(PAGE_SIZE)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Content> = withContext(Dispatchers.IO) {
        try {
            val page = params.key
            val productList = productApi.searchProduct(
                keyword = productSearchRequest.keyword,
                maxPrice = productSearchRequest.maxPrice,
                minPrice = productSearchRequest.minPrice,
                offsetProductId = page,
                orderBy = productSearchRequest.orderBy,
                pageSize = PAGE_SIZE,
                pbOnly = productSearchRequest.pbOnly,
                productCategoryTypeList = productSearchRequest.productCategoryTypeList,
                promotionRetailerList = productSearchRequest.promotionRetailerList,
                promotionTypeList = productSearchRequest.promotionTypeList
            )
            println("current: $page / last : ${productList.lastId}")
            LoadResult.Page(
                data = productList.content,
                prevKey = page,
                nextKey = if (page == productList.lastId) null else productList.lastId
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    companion object {
        const val PAGE_SIZE = 10
    }
}
