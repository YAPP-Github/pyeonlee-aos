package com.peonlee.feature.detail

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.feature.detail.databinding.ActivityProductCommentsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductCommentsActivity : BaseActivity<ActivityProductCommentsBinding>() {
    companion object {
        private const val DEFAULT_PRODUCT_ID = -1
        private const val EXTRA_PRODUCT_ID = "extra_product_id"

        fun startActivity(context: Context, productId: Int) {
            context.startActivity(
                Intent(context, ProductCommentsActivity::class.java).apply {
                    putExtra(EXTRA_PRODUCT_ID, productId)
                }
            )
        }
    }

    private val viewModel: ProductCommentsViewModel by viewModels()
    private val productId by lazy { intent.getIntExtra(EXTRA_PRODUCT_ID, DEFAULT_PRODUCT_ID) }
    private val adapter by lazy {
        ProductCommentsPagingAdapter {
            ReviewManageDialog.newInstance(productId).run {
                show(supportFragmentManager, tag)
            }
        }
    }

    override fun bindingFactory(): ActivityProductCommentsBinding {
        return ActivityProductCommentsBinding.inflate(layoutInflater)
    }

    override fun initViews() = with(binding) {
        if (productId == DEFAULT_PRODUCT_ID) {
            finish()
            return
        }
        ivBackBtn.setOnClickListener {
            finish()
        }
        rvProductComments.adapter = adapter
    }

    override fun bindViews() {
        lifecycleScope.launch {
            viewModel.getProductComments(productId).collectLatest {
                adapter.submitData(it)
            }
        }
    }
}
