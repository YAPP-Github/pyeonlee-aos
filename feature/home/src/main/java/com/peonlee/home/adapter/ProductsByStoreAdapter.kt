package com.peonlee.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.peonlee.core.ui.Navigator
import com.peonlee.core.ui.adapter.SingleTypeListAdapter
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.home.adapter.viewholder.product.ProductsByStoreViewHolder
import com.peonlee.home.databinding.PagerItemProductsByStoreBinding
import com.peonlee.home.model.product.ProductsByStoreUiModel

class ProductsByStoreAdapter(private val navigator: Navigator) : SingleTypeListAdapter<ProductsByStoreUiModel>({ it.stores.name }) {
    override fun onCreateViewHolder(parent: ViewGroup): CommonViewHolder<ProductsByStoreUiModel> {
        return ProductsByStoreViewHolder(
            PagerItemProductsByStoreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            navigator
        )
    }
}
