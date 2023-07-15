package com.peonlee.explore.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peonlee.core.ui.base.BaseBottomSheetFragment
import com.peonlee.core.ui.designsystem.selector.SmallSelector
import com.peonlee.explore.databinding.ItemFilterChipBinding
import com.peonlee.explore.databinding.ItemSelectorFilterBinding
import com.peonlee.explore.databinding.LayoutSelectorFilterBinding
import com.peonlee.model.product.ProductSearchConditionUiModel
import com.peonlee.model.type.Category
import com.peonlee.model.type.CategoryFilter

class CategoryFilterBottomSheetFragment(
    private val onCategorySelect: (List<Category>) -> Unit
) : BaseBottomSheetFragment("카테고리") {

    private var selectedCategory = mutableListOf<Category>()

    override fun getFilterLayout(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): View {
        val listLayout = LayoutSelectorFilterBinding.inflate(layoutInflater, parent, false).root

        CategoryFilter.values().forEach { categoryFilter ->
            listLayout.addView(
                ItemSelectorFilterBinding.inflate(layoutInflater, listLayout, false).apply {
                    tvTitle.text = categoryFilter.title
                    categoryFilter.filters.forEach { category ->
                        flexEventChip.addView(
                            ItemFilterChipBinding.inflate(layoutInflater).apply {
                                root.text = category.categoryName
                                if (category in selectedCategory) root.setFillColor() else root.setCancelColor()
                                root.setOnClickListener { onSelectCategory(root, category) }
                            }.root
                        )
                    }
                }.root
            )
        }
        return listLayout
    }

    override fun onClickComplete() {
        onCategorySelect(selectedCategory)
    }

    override fun setChangedFilter(productSearchCondition: ProductSearchConditionUiModel): BaseBottomSheetFragment {
        selectedCategory = productSearchCondition.categories?.toMutableList() ?: mutableListOf()
        return this
    }

    private fun onSelectCategory(
        selector: SmallSelector, category: Category
    ) {
        if (category in selectedCategory) {
            selector.setCancelColor()
            selectedCategory.remove(category)
        } else {
            selector.setFillColor()
            selectedCategory.add(category)
        }
    }
}
