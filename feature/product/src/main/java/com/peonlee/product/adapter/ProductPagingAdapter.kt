package com.peonlee.product.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.peonlee.core.ui.Navigator
import com.peonlee.core.ui.databinding.ListItemProductBinding
import com.peonlee.core.ui.viewholder.product.ProductViewHolder
import com.peonlee.model.product.ProductUiModel

class ProductPagingAdapter(
    private val rootLayoutParams: ConstraintLayout.LayoutParams,
    private val navigator: Navigator
) : PagingDataAdapter<ProductUiModel, ProductViewHolder>(PRODUCT_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            rootLayoutParams,
            ListItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            navigator
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.onBindView(item)
    }

    companion object {
        private val PRODUCT_DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductUiModel>() {
            override fun areItemsTheSame(oldItem: ProductUiModel, newItem: ProductUiModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ProductUiModel, newItem: ProductUiModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}
