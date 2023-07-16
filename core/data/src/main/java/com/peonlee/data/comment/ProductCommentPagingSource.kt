package com.peonlee.data.comment

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.peonlee.data.model.Comment

class ProductCommentPagingSource(
    private val productId: Int,
    private val commentApi: CommentApi
) : PagingSource<Int, Comment>() {

    override fun getRefreshKey(state: PagingState<Int, Comment>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val closestPageToPosition = state.closestPageToPosition(anchorPosition)
            closestPageToPosition?.prevKey?.plus(PAGE_SIZE)
                ?: closestPageToPosition?.nextKey?.minus(PAGE_SIZE)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comment> {
        val page = params.key
        return try {
            val commentList = commentApi.getProductComments(productId, page).copy()
            LoadResult.Page(
                data = commentList.content,
                prevKey = page,
                nextKey = if (page == commentList.lastId) null else commentList.lastId
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    companion object {
        const val PAGE_SIZE = 20
    }
}
