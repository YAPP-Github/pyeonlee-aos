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
    override fun onBindView(item: ConditionalProductsUiModel) = with(binding) {
        val productAdapter = ProductAdapter()
        layoutProducts.adapter = productAdapter
        productAdapter.submitList(item.products)

        // 기존에 적용된 itemDecorationCount 가 있다면 제거
        if (layoutProducts.itemDecorationCount > 0) {
            layoutProducts.removeItemDecorationAt(0)
        }
        layoutProducts.addItemDecoration(
            ContentPaddingDecoration(PaddingValues(left = 8))
        )

        btnMoreProducts.text = getStringWithArgs(
            R.string.item_conditional_products_button_text,
            item.condition.title
        )

    }
}
