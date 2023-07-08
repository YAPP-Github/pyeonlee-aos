package com.peonlee.model.product

/**
 * 상품 리스트 에 보여줄 상품 정보
 */
data class ProductUiModel(
    val id: Long, // 상품 id
    val imageUrl: String?, // 상품 이미지 (없을 수도 있음)
    val name: String, // 상품 이름
    val price: Int, // 상품 가격
    val percentage: Int, // 평점
    val reviewCnt: Int, // 리뷰 개수
    val isEvent: Boolean // 행사 여부
)

val PRODUCTS_TEST_DOUBLE = (1..10).map {
    ProductUiModel(
        id = it.toLong(),
        imageUrl = null,
        name = "상품 이름 $it",
        price = 1000 * it,
        percentage = 36,
        reviewCnt = 24,
        isEvent = it % 2 == 0
    )
}
