package com.peonlee.data.comment

import androidx.paging.PagingData
import com.peonlee.data.model.Comment
import kotlinx.coroutines.flow.Flow

interface CommentRepository {
    fun getProductCommentsPaging(productId: Int): Flow<PagingData<Comment>>
}
