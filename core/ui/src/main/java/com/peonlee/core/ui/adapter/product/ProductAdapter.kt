package com.peonlee.core.ui.adapter.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
import com.peonlee.core.ui.adapter.SingleTypeListAdapter
import com.peonlee.core.ui.databinding.ListItemProductBinding
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.core.ui.viewholder.product.ProductViewHolder
import com.peonlee.model.product.ProductUiModel

/**
 * 상품을 위한 Single Item Adapter
 */
class ProductAdapter(
    private val rootLayoutParams: LayoutParams
) : SingleTypeListAdapter<ProductUiModel>({ it.id }) {
    override fun onCreateViewHolder(parent: ViewGroup): CommonViewHolder<ProductUiModel> {
        return ProductViewHolder(
            rootLayoutParams,
            ListItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}
