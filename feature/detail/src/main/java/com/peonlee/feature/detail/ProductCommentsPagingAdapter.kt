package com.peonlee.feature.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.feature.detail.ProductDetailListItem.Review
import com.peonlee.feature.detail.ProductDetailListItem.ReviewHeader
import com.peonlee.feature.detail.databinding.ListItemCommentBinding
import com.peonlee.feature.detail.databinding.ListItemCommentHeaderBinding

class ProductCommentsPagingAdapter(
    private val showReviewManageDialog: (Review) -> Unit
) : PagingDataAdapter<ReviewItem, CommonViewHolder<ReviewItem>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder<ReviewItem> {
        return if (viewType == 0) {
            CommentViewHolder(
                showReviewManageDialog,
                ListItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        } else {
            CommentHeaderViewHolder(
                ListItemCommentHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: CommonViewHolder<ReviewItem>, position: Int) {
        val item = getItem(position) ?: return
        holder.onBindView(item)
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Review -> 0
            is ReviewHeader -> 1
            null -> -1
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ReviewItem>() {
            override fun areItemsTheSame(oldItem: ReviewItem, newItem: ReviewItem): Boolean {
                return if (oldItem is Review && newItem is Review) {
                    oldItem.id == newItem.id
                } else {
                    oldItem is ReviewHeader && newItem is ReviewHeader
                }
            }

            override fun areContentsTheSame(oldItem: ReviewItem, newItem: ReviewItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
