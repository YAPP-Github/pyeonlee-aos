package com.peonlee.core.ui.viewholder

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
    internal fun ListAdapter<*, *>.getItem(position: Int): Any? {
        return try {
            currentList[position]
        } catch (e: Exception) {
            null
        }
    }

    @Suppress("UNCHECKED_CAST")
    inline fun ListAdapter<*, *>.getItem(action: (item: E) -> Unit) {
        getItemPosition()?.let { index ->
            val item = (getItem(index) as? E) ?: return
            action(item)
        }
    }

    @Suppress("UNCHECKED_CAST")
    inline fun ListAdapter<*, *>.getItemIndexed(action: (index: Int, item: E) -> Unit) {
        getItemPosition()?.let { index ->
            val item = (getItem(index) as? E) ?: return
            action(index, item)
        }
    }
}
