package com.peonlee.core.ui

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.peonlee.model.product.ProductSearchConditionUiModel

interface Navigator {
    fun navigateToProductDetail(context: Context, productId: Int)

    fun navigateToProductComments(
        context: Context,
        productId: Int,
        imageUrl: String,
        productName: String,
        price: Int,
        totalCommentsCount: Int,
        launcher: ActivityResultLauncher<Intent>
    )

    fun navigateToEditReview(
        context: Context,
        productId: Int,
        imageUrl: String,
        productName: String,
        price: Int,
        content: String?,
        launcher: ActivityResultLauncher<Intent>
    )

    /**
     * 검색 화면으로 이동
     */
    fun navigateToSearch(context: Context)

    fun navigateToLogin(context: Context)
    fun navigateToMain(context: Context)

    /**
     * 탐색 화면으로 이동
     * @param productSearchConditionUiModel 기본 탐색 조건
     */
    fun navigateToExplore(productSearchConditionUiModel: ProductSearchConditionUiModel?)

    /**
     * 닉네임 변경 화면으로 이동
     */
    fun navigateToEditNickname(context: Context, nickname: String, userId: Int)
}
