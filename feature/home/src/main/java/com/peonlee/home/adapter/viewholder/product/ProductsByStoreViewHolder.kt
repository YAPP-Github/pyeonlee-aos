package com.peonlee.home.adapter.viewholder.product

import android.view.LayoutInflater
import android.widget.GridLayout
import android.widget.GridLayout.LayoutParams
import com.peonlee.core.ui.databinding.ListItemProductBinding
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.core.ui.viewholder.product.ProductViewHolder
import com.peonlee.home.databinding.PagerItemProductsByStoreBinding
import com.peonlee.home.model.product.ProductsByStoreUiModel

private const val GRID_COLUMN = 3

class ProductsByStoreViewHolder(
    private val binding: PagerItemProductsByStoreBinding
) : CommonViewHolder<ProductsByStoreUiModel>(binding) {
    override fun onBindView(item: ProductsByStoreUiModel) = with(binding) {
        item.products.forEachIndexed { index, product ->
            val params = LayoutParams().apply {
                rowSpec = GridLayout.spec(index / GRID_COLUMN, 1f)
                columnSpec = GridLayout.spec(index % GRID_COLUMN, 1f)
                width = 0
            }
            layoutProducts.addView(
                ProductViewHolder(
                    ListItemProductBinding.inflate(LayoutInflater.from(itemView.context))
                ).also {
                    it.onBindView(product)
                }.itemView,
                params
            )
        }
    }
}
