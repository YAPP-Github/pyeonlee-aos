package com.peonlee.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertHeaderItem
import androidx.paging.map
import com.peonlee.data.comment.CommentRepository
import com.peonlee.data.model.Comment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ProductCommentsViewModel @Inject constructor(
    private val commentRepository: CommentRepository
) : ViewModel() {

    fun getProductComments(totalCommentsCount: Int, productId: Int): Flow<PagingData<ReviewItem>> {
        return try {
            commentRepository.getProductCommentsPaging(productId)
                .map { data ->
                    data.map<Comment, ReviewItem> { comment ->
                        ProductDetailListItem.Review(
                            id = comment.productCommentId.toLong(),
                            nickname = comment.memberNickName,
                            writeDate = comment.createdAt,
                            likeType = comment.productLikeType,
                            reviewText = comment.content,
                            isLike = comment.liked,
                            likeCount = comment.likeCount,
                            isMine = comment.isOwner
                        )
                    }.insertHeaderItem(item = ProductDetailListItem.ReviewHeader(0, totalCommentsCount))
                }.cachedIn(viewModelScope)
        } catch (exception: Exception) {
            flowOf(PagingData.empty())
        }
    }
}
