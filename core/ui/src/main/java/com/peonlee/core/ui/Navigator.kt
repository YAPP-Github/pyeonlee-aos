package com.peonlee.core.ui

import android.content.Context

interface Navigator {
    fun navigateToProductDetail(context: Context, productId: Int)
    fun navigateToEditReview(context: Context)
}
