package com.peonlee.core.ui

import android.view.ViewGroup
import java.lang.reflect.ParameterizedType

interface ListItem {
    val id: Long
    val viewType: Enum<*>
}

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
