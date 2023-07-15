package com.peonlee

import android.content.Context
import android.content.Intent
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

    override fun navigateToEditReview(context: Context) {
        context.startActivity(Intent(context, EditReviewActivity::class.java))
    }
}
