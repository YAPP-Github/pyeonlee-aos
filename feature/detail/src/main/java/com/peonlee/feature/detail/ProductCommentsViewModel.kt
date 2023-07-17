package com.peonlee.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.peonlee.data.comment.CommentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ProductCommentsViewModel @Inject constructor(
    private val commentRepository: CommentRepository
) : ViewModel() {

    fun getProductComments(productId: Int): Flow<PagingData<ProductDetailListItem.Review>> {
        return try {
            commentRepository.getProductCommentsPaging(productId).map {
                it.map { comment ->
                    ProductDetailListItem.Review(
                        id = comment.productCommentId.toLong(),
                        nickname = comment.member.nickname,
                        writeDate = comment.createdAt,
                        likeType = comment.likeType,
                        reviewText = comment.content,
                        isLike = comment.liked,
                        likeCount = comment.commentLikeCount,
                        isMine = comment.isOwner
                    )
                }
            }.cachedIn(viewModelScope)
        } catch (exception: Exception) {
            flowOf(PagingData.empty())
        }
    }
}
