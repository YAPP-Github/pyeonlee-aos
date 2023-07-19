package com.peonlee.core.ui.viewholder

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class CommonViewHolder<out E>(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun onBindView(item: @UnsafeVariance E)

    @PublishedApi
    internal fun getItemPosition(): Int? {
        return bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION }
    }

    @PublishedApi
    internal fun getItem(position: Int): Any? {
        return when (val adapter = bindingAdapter) {
            is ListAdapter<*, *> -> {
                adapter.currentList[position]
            }
            is PagingDataAdapter<*, *> -> {
                adapter.peek(position)
            }
            else -> {
                throw IllegalAccessException("This function can only be used on ViewHolders inside a ListAdapter.")
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    inline fun getItem(action: (item: E) -> Unit) {
        getItemPosition()?.let { index ->
            val item = (getItem(index) as? E) ?: return
            action(item)
        }
    }

    @Suppress("UNCHECKED_CAST")
    inline fun getItemIndexed(action: (index: Int, item: E) -> Unit) {
        getItemPosition()?.let { index ->
            val item = (getItem(index) as? E) ?: return
            action(index, item)
        }
    }
}
