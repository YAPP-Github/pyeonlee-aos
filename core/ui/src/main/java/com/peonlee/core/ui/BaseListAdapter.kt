package com.peonlee.core.ui

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding

abstract class BaseListAdapter<T : Any>(checkParameter: (T) -> Any?) : ListAdapter<T, CommonViewHolder<T>>(BaseDiffUtil<T>(checkParameter)) {
    final override fun onBindViewHolder(holder: CommonViewHolder<T>, position: Int) {
        holder.onBindView(currentList[position])
    }

    final override fun getItemCount(): Int = currentList.size

    private class BaseDiffUtil<T : Any>(private val checkParameter: (T) -> Any?) : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.isEqualParameter(newItem) {
                checkParameter(it)
            }
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return compareValues(oldItem, newItem)
        }

        private fun <T : Any> compareValues(a: T, b: T): Boolean {
            return a == b
        }

        private fun <T : Any> T.isEqualParameter(other: T, parameter: (T) -> Any?): Boolean {
            if (this::class != other::class) return false
            return parameter(this) == parameter(other)
        }
    }

    open inner class ViewOnlyViewHolder(binding: ViewBinding) : CommonViewHolder<T>(binding) {
        final override fun onBindView(item: T) = Unit
    }
}
