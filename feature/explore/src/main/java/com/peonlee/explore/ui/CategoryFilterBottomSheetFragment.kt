package com.peonlee.explore.ui

import android.view.View
import android.view.ViewGroup
import com.peonlee.core.ui.base.BaseBottomSheetFragment
import com.peonlee.core.ui.designsystem.selector.SmallSelector
import com.peonlee.explore.databinding.ItemFilterChipBinding
import com.peonlee.explore.databinding.ItemSelectorFilterBinding
import com.peonlee.explore.databinding.LayoutSelectorFilterBinding

class CategoryFilterBottomSheetFragment : BaseBottomSheetFragment("카테고리") {

    private val selectedCategory = mutableListOf<Category>()

    override fun getFilterLayout(parent: ViewGroup): View {
        val listLayout = LayoutSelectorFilterBinding.inflate(layoutInflater, parent, false).root

        CategoryFilter.values().forEach { categoryFilter ->
            listLayout.addView(
                ItemSelectorFilterBinding.inflate(layoutInflater, listLayout, false).apply {
                    tvTitle.text = categoryFilter.title
                    categoryFilter.filters.forEach { category ->
                        flexEventChip.addView(
                            ItemFilterChipBinding.inflate(layoutInflater).apply {
                                root.text = category.categoryName
                                root.setCancelColor()
                                root.setOnClickListener { onSelectCategory(root, category) }
                            }.root
                        )
                    }
                }.root
            )
        }
        return listLayout
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

private enum class CategoryFilter(
    val title: String,
    val filters: List<Category>
) {
    CONVENIENCE("간편식사", convenience),
    INSTANCE("즉석조리", instance),
    SNACK("과자류", snack),
    ICE_CREAM("아이스크림", icecream),
    FOOD("식품", food),
    DRINK("음료", drink),
    PB("PB", pb)
}

private enum class Category(
    val categoryName: String,
    val categoryType: String
) {
    DOSIRAK("도시락", "DOSIRAK"),
    SANDWICH("샌드위치/햄버거", "SANDWICH"),
    GIMBAP("주먹밥/김밥", "GIMBAP"),
    FRIES("튀김류", "FRIES"),
    BAKERY("베이커리", "BAKERY"),
    INSTANT_COFFEE("즉석커피", "INSTANT_COFFEE"),
    BISCUIT("스낵/비스켓", "BISCUIT"),
    DESSERT("빵/디저트", "DESSERT"),
    CANDY("껌/초콜릿/캔디", "CANDY"),
    ICE_CREAM("아이스크림", "ICE_CREAM"),
    INSTANT_MEAL("가공식사", "INSTANT_MEAL"),
    MUNCHIES("안주류", "MUNCHIES"),
    INGREDIENT("식재료", "INGREDIENT"),
    DRINK("음료", "DRINK"),
    ICED_DRINK("아이스드링크", "ICED_DRINK"),
    DIARY("유제품", "DIARY"),
    PB("PB 상품", "PB")
}

private val convenience = listOf(Category.DOSIRAK, Category.SANDWICH, Category.GIMBAP)
private val instance = listOf(Category.FRIES, Category.BAKERY, Category.INSTANT_COFFEE)
private val snack = listOf(Category.BISCUIT, Category.DESSERT, Category.CANDY)
private val icecream = listOf(Category.ICE_CREAM)
private val food = listOf(Category.INSTANT_MEAL, Category.MUNCHIES, Category.INGREDIENT)
private val drink = listOf(Category.DRINK, Category.ICED_DRINK, Category.DIARY)
private val pb = listOf(Category.PB)
