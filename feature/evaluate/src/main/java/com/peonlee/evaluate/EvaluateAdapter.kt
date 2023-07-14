package com.peonlee.evaluate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.peonlee.data.model.Content
import com.peonlee.evaluate.databinding.ListItemEvaluateBinding

class EvaluateAdapter : PagingDataAdapter<Content, EvaluateViewHolder>(EVALUATE_DIFF_CALLBACK) {
    override fun onBindViewHolder(holder: EvaluateViewHolder, position: Int) {
        getItem(position)?.let { product ->
            holder.onBindView(product)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvaluateViewHolder {
        return EvaluateViewHolder(
            ListItemEvaluateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    companion object {
        private val EVALUATE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Content>() {
            override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean {
                return oldItem.productId == newItem.productId
            }

            override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean {
                return oldItem == newItem
            }
        }
    }
}
