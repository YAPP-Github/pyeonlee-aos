package com.peonlee.core.ui.adapter

import android.view.ViewGroup
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.model.ListItem
import java.lang.reflect.ParameterizedType

abstract class MultiTypeListAdapter<T : ListItem, E : Enum<E>> : BaseListAdapter<T>({ it.id }) {
    private val enumValues by lazy { getEnumClass().enumConstants ?: throw Exception() }

    final override fun getItemViewType(position: Int): Int {
        return currentList[position].viewType.ordinal
    }

    abstract fun onCreateViewHolder(viewType: E, parent: ViewGroup): CommonViewHolder<T>

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder<T> {
        return onCreateViewHolder(enumValues[viewType], parent)
    }

    @Suppress("UNCHECKED_CAST")
    private fun getEnumClass(): Class<E> {
        val genericSuperclass = javaClass.genericSuperclass
        val type = (genericSuperclass as ParameterizedType).actualTypeArguments[1]
        return type as Class<E>
    }
}
