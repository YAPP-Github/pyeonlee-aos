package com.peonlee.model.type

enum class CategoryFilter(
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

enum class Category(
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

val convenience = listOf(Category.DOSIRAK, Category.SANDWICH, Category.GIMBAP)
val instance = listOf(Category.FRIES, Category.BAKERY, Category.INSTANT_COFFEE)
val snack = listOf(Category.BISCUIT, Category.DESSERT, Category.CANDY)
val icecream = listOf(Category.ICE_CREAM)
val food = listOf(Category.INSTANT_MEAL, Category.MUNCHIES, Category.INGREDIENT)
val drink = listOf(Category.DRINK, Category.ICED_DRINK, Category.DIARY)
val pb = listOf(Category.PB)
