package com.peonlee.core.ui.adapter

import android.view.ViewGroup
import com.peonlee.core.ui.viewholder.CommonViewHolder

abstract class SingleTypeListAdapter<T : Any>(checkParameter: (T) -> Any?) : BaseListAdapter<T>(checkParameter) {
    final override fun getItemViewType(position: Int): Int {
        return 0
    }

    abstract fun onCreateViewHolder(parent: ViewGroup): CommonViewHolder<T>

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder<T> {
        return onCreateViewHolder(parent)
    }
}
