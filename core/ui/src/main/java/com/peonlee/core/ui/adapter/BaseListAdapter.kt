package com.peonlee.core.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.core.ui.viewholder.ViewOnlyViewHolder

abstract class BaseListAdapter<T : Any>(checkParameter: (T) -> Any?) : ListAdapter<T, CommonViewHolder<T>>(BaseDiffUtil<T>(checkParameter)) {
    final override fun onBindViewHolder(holder: CommonViewHolder<T>, position: Int) {
        if (holder is ViewOnlyViewHolder) return
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
}
