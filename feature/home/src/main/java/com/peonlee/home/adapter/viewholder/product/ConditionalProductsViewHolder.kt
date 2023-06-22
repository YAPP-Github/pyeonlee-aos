package com.peonlee.home.adapter.viewholder.product

import com.peonlee.core.ui.adapter.decoration.ContentPaddingDecoration
import com.peonlee.core.ui.adapter.product.ProductAdapter
import com.peonlee.core.ui.extensions.getStringWithArgs
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.home.R
import com.peonlee.home.databinding.ListItemConditionalProductsBinding
import com.peonlee.home.model.product.ConditionalProductsUiModel
import com.peonlee.model.util.PaddingValues

class ConditionalProductsViewHolder(
    private val binding: ListItemConditionalProductsBinding
) : CommonViewHolder<ConditionalProductsUiModel>(binding) {
    private var productAdapter = ProductAdapter()

    init {
        with(binding) {
            layoutProducts.setHasFixedSize(true)
            layoutProducts.adapter = productAdapter
            // 기존에 적용된 itemDecorationCount 가 있다면 제거
            if (layoutProducts.itemDecorationCount > 0) {
                layoutProducts.removeItemDecorationAt(0)
            }
            layoutProducts.addItemDecoration(
                ContentPaddingDecoration(PaddingValues(left = 8))
            )
        }
    }

    override fun onBindView(item: ConditionalProductsUiModel) = with(binding) {
        if (item.products != productAdapter.currentList) {
            productAdapter.submitList(item.products)
        }
        btnMoreProducts.text = getStringWithArgs(
            R.string.item_conditional_products_button_text,
            item.condition.title
        )
    }
}
