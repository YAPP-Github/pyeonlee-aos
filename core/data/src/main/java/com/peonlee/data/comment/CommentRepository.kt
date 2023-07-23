package com.peonlee.data.comment

import androidx.paging.PagingData
import com.peonlee.data.Result
import com.peonlee.data.model.Comment
import kotlinx.coroutines.flow.Flow

interface CommentRepository {

    suspend fun getProductComments(productId: Int): Result<List<Comment>>
    fun getProductCommentsPaging(productId: Int): Flow<PagingData<Comment>>

    suspend fun saveComment(productId: Int, review: String): Result<Unit>

    suspend fun editComment(productId: Int, review: String): Result<Unit>

    suspend fun deleteComment(productId: Int): Result<Unit>

    suspend fun likeComment(commentId: Int): Result<Unit>

    suspend fun unlikeComment(commentId: Int): Result<Unit>
}
