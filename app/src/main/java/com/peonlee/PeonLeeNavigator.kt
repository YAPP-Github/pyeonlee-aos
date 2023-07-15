package com.peonlee

import android.content.Context
import com.peonlee.core.ui.Navigator
import com.peonlee.feature.detail.ProductDetailActivity
import com.peonlee.review.edit.EditReviewActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeonLeeNavigator @Inject constructor() : Navigator {
    override fun navigateToProductDetail(context: Context, productId: Int) {
        ProductDetailActivity.startActivity(context, productId)
    }

    override fun navigateToEditReview(context: Context, productId: Int, imageUrl: String, productName: String, price: Int) {
        EditReviewActivity.startActivity(context, productId, imageUrl, productName, price)
    }
}
