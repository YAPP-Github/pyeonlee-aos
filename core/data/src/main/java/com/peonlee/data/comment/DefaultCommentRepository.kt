package com.peonlee.data.comment

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.peonlee.data.Result
import com.peonlee.data.model.Comment
import com.peonlee.data.model.review.SaveReviewRequest
import com.peonlee.data.setResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultCommentRepository @Inject constructor(
    private val commentApi: CommentApi
) : CommentRepository {
    override suspend fun getProductComments(productId: Int) = setResult {
        commentApi.getProductComments(productId).content
    }

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

    override suspend fun saveComment(productId: Int, review: String) = setResult {
        commentApi.saveComment(
            productId = productId,
            SaveReviewRequest(review = review)
        )
    }

    override suspend fun editComment(productId: Int, review: String) = setResult {
        commentApi.editComment(
            productId = productId,
            SaveReviewRequest(review = review)
        )
    }

    override suspend fun deleteComment(productId: Int): Result<Unit> = setResult {
        commentApi.deleteComment(productId)
    }

    override suspend fun likeComment(commentId: Int): Result<Unit> = setResult {
        commentApi.likeComment(commentId)
    }

    override suspend fun unlikeComment(commentId: Int): Result<Unit> = setResult {
        commentApi.unlikeComment(commentId)
    }
}
