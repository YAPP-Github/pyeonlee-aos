package com.peonlee.core.ui

import android.view.ViewGroup

abstract class SingleTypeListAdapter<T : Any>(checkParameter: (T) -> Any?) : BaseListAdapter<T>(checkParameter) {
    final override fun getItemViewType(position: Int): Int {
        return 0
    }

    abstract fun onCreateViewHolder(parent: ViewGroup): CommonViewHolder<T>

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder<T> {
        return onCreateViewHolder(parent)
    }
}
