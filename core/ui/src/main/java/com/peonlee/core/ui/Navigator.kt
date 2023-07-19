package com.peonlee.core.ui

import android.content.Context

interface Navigator {
    fun navigateToProductDetail(context: Context, productId: Int)

    fun navigateToProductComments(context: Context, productId: Int, imageUrl: String, productName: String, price: Int, totalCommentsCount: Int)
    fun navigateToEditReview(context: Context, productId: Int, imageUrl: String, productName: String, price: Int, content: String?)
}
