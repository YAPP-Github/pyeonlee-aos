package com.peonlee.core.ui

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher

interface Navigator {
    fun navigateToProductDetail(context: Context, productId: Int)

    fun navigateToProductComments(context: Context, productId: Int, imageUrl: String, productName: String, price: Int, totalCommentsCount: Int)
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
    fun navigateToExplore(context: Context)
}
