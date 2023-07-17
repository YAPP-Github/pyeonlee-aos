package com.peonlee.data.comment

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.peonlee.data.model.Comment
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultCommentRepository @Inject constructor(
    private val commentApi: CommentApi
) : CommentRepository {
    override fun getProductCommentsPaging(productId: Int): Flow<PagingData<Comment>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ProductCommentPagingSource(productId, commentApi)
            }
        ).flow
    }
}
