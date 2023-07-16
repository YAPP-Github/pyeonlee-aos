package com.peonlee.home.adapter.viewholder.product

import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.GridLayout
import android.widget.GridLayout.LayoutParams
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintProperties.WRAP_CONTENT
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.peonlee.common.ext.dpToPx
import com.peonlee.core.ui.Navigator
import com.peonlee.core.ui.databinding.ListItemProductBinding
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.core.ui.viewholder.product.ProductViewHolder
import com.peonlee.home.databinding.PagerItemProductsByStoreBinding
import com.peonlee.home.model.product.ProductsByStoreUiModel

private const val GRID_COLUMN = 3
private val productLayoutParams = ConstraintLayout.LayoutParams(
    MATCH_PARENT, // width
    WRAP_CONTENT // height
)

class ProductsByStoreViewHolder(
    private val binding: PagerItemProductsByStoreBinding,
    private val navigator: Navigator
) : CommonViewHolder<ProductsByStoreUiModel>(binding) {
    override fun onBindView(item: ProductsByStoreUiModel) = with(binding) {
        if (item.products.isEmpty()) {
            tvEmpty.isVisible = true
        } else {
            tvEmpty.isGone = true
            item.products.forEachIndexed { index, product ->
                val params = LayoutParams().apply {
                    rowSpec = GridLayout.spec(index / GRID_COLUMN)
                    columnSpec = GridLayout.spec(index % GRID_COLUMN, 1f)
                    width = 0
                }
                /**
                 * addView를 하기 전에 해당 layout에 child view가 있는 경우
                 * 중복으로 생성 되는 것을 방지 하기 위해 기존의 child view 들은 전부 제거
                 */
                layoutProducts.addView(
                    ProductViewHolder(
                        layoutParams = productLayoutParams,
                        ListItemProductBinding.inflate(LayoutInflater.from(itemView.context)),
                        navigator
                    ).also {
                        it.onBindView(product)
                    }.itemView,
                    params
                )
            }
        }
    }
}
