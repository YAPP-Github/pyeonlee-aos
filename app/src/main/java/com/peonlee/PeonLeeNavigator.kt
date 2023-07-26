package com.peonlee

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.peonlee.core.ui.Navigator
import com.peonlee.explore.ExploreActivity
import com.peonlee.feature.detail.ProductCommentsActivity
import com.peonlee.feature.detail.ProductDetailActivity
import com.peonlee.feature.detail.ProductExtra
import com.peonlee.login.LoginActivity
import com.peonlee.model.product.ProductSearchConditionUiModel
import com.peonlee.review.edit.EditReviewActivity
import com.peonlee.user.modify.ModifyUserNicknameActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeonLeeNavigator @Inject constructor() : Navigator {
    override fun navigateToProductDetail(context: Context, productId: Int) {
        ProductDetailActivity.startActivity(context, productId)
    }

    override fun navigateToProductComments(
        context: Context,
        productId: Int,
        imageUrl: String,
        productName: String,
        price: Int,
        totalCommentsCount: Int,
        launcher: ActivityResultLauncher<Intent>
    ) {
        launcher.launch(ProductCommentsActivity.newIntent(context, ProductExtra(productId, imageUrl, productName, price), totalCommentsCount))
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

    override fun navigateToSearch(context: Context) {
        ExploreActivity.startActivity(context)
    }

    override fun navigateToLogin(context: Context) {
        LoginActivity.startActivity(context)
    }

    override fun navigateToExplore(
        productSearchConditionUiModel: ProductSearchConditionUiModel?
    ) {
    }

    override fun navigateToEditNickname(context: Context, nickname: String, userId: Int) {
        context.startActivity(ModifyUserNicknameActivity.getIntent(context, nickname, userId))
    }
}
