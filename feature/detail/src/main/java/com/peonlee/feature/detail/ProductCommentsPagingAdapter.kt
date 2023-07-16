package com.peonlee.feature.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.peonlee.feature.detail.ProductDetailListItem.Review
import com.peonlee.feature.detail.databinding.ListItemReviewBinding

class ProductCommentsPagingAdapter(
    private val showReviewManageDialog: (Review) -> Unit
) : PagingDataAdapter<Review, ReviewViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(
            showReviewManageDialog,
            ListItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.onBindView(item)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Review>() {
            override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
                return oldItem == newItem
            }
        }
    }
}
