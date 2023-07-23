package com.peonlee.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertHeaderItem
import androidx.paging.map
import com.peonlee.data.comment.CommentRepository
import com.peonlee.data.handle
import com.peonlee.data.model.Comment
import com.peonlee.feature.detail.ProductDetailListItem.Review
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductCommentsViewModel @Inject constructor(
    private val commentRepository: CommentRepository
) : ViewModel() {
    private val localDataList = MutableStateFlow<List<Review>>(emptyList())

    fun getProductComments(totalCommentsCount: Int, productId: Int): Flow<PagingData<ReviewItem>> {
        return try {
            commentRepository.getProductCommentsPaging(productId)
                .map { data ->
                    val newData = data.map<Comment, ReviewItem> { comment ->
                        Review(
                            id = comment.productCommentId.toLong(),
                            nickname = comment.memberNickName,
                            writeDate = comment.createdAt,
                            likeType = comment.productLikeType,
                            reviewText = comment.content,
                            isLike = comment.liked,
                            likeCount = comment.likeCount,
                            isMine = comment.isOwner
                        )
                    }
                    if (totalCommentsCount > 0) {
                        return@map newData.insertHeaderItem(item = ProductDetailListItem.ReviewHeader(0, totalCommentsCount))
                    }
                    newData
                }.cachedIn(viewModelScope).combine(localDataList) { paging, local ->
                    local.fold(paging) { acc, event ->
                        acc.map {
                            if (it is Review && event.id == it.id) {
                                it.copy(isLike = event.isLike, likeCount = event.likeCount)
                            } else {
                                it
                            }
                        }
                    }
                }
        } catch (exception: Exception) {
            flowOf(PagingData.empty())
        }
    }

    fun updateComment(comment: Review) {
        viewModelScope.launch {
            if (comment.isLike) {
                commentRepository.unlikeComment(comment.id.toInt()).handle({
                    updateCommentView(comment)
                })
            } else {
                commentRepository.likeComment(comment.id.toInt()).handle({
                    updateCommentView(comment)
                })
            }
        }
    }

    private fun updateCommentView(comment: Review) = with(comment) {
        localDataList.value = listOf(comment.copy(likeCount = likeCount + if (isLike.not()) 1 else -1, isLike = isLike.not()))
    }
}
