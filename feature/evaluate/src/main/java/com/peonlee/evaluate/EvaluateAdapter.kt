package com.peonlee.evaluate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.peonlee.data.model.Product
import com.peonlee.evaluate.databinding.ListItemEvaluateBinding

class EvaluateAdapter(private val onClickEvent: (Int) -> Unit) : PagingDataAdapter<Product, EvaluateViewHolder>(EVALUATE_DIFF_CALLBACK) {
    override fun onBindViewHolder(holder: EvaluateViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.onBindView(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvaluateViewHolder {
        return EvaluateViewHolder(ListItemEvaluateBinding.inflate(LayoutInflater.from(parent.context), parent, false), onClickEvent)
    }

    companion object {
        private val EVALUATE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.productId == newItem.productId
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }
        }
    }
}
