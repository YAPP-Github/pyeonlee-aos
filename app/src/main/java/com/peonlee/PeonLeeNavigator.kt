package com.peonlee

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.peonlee.core.ui.Navigator
import com.peonlee.feature.detail.ProductCommentsActivity
import com.peonlee.feature.detail.ProductDetailActivity
import com.peonlee.feature.detail.ProductExtra
import com.peonlee.review.edit.EditReviewActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeonLeeNavigator @Inject constructor() : Navigator {
    override fun navigateToProductDetail(context: Context, productId: Int) {
        ProductDetailActivity.startActivity(context, productId)
    }

    override fun navigateToProductComments(context: Context, productId: Int, imageUrl: String, productName: String, price: Int, totalCommentsCount: Int) {
        ProductCommentsActivity.startActivity(context, ProductExtra(productId, imageUrl, productName, price), totalCommentsCount)
    }

    override fun navigateToEditReview(
        context: Context,
        productId: Int,
        imageUrl: String,
        productName: String,
        price: Int,
        content: String?,
        launcher: ActivityResultLauncher<Intent>
    ) {
        launcher.launch(EditReviewActivity.newIntent(context, productId, imageUrl, productName, price, content))
    }
}
