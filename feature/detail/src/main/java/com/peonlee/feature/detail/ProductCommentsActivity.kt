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
class ProductCommentsActivity : BaseActivity<ActivityProductCommentsBinding>(), CommentStateChangeListener {
    companion object {
        private const val EXTRA_PRODUCT = "extra_product"
        private const val EXTRA_TOTAL_COMMENTS_COUNT = "extra_total_comments_count"

        fun startActivity(context: Context, productExtra: ProductExtra, totalCommentsCount: Int) {
            context.startActivity(
                Intent(context, ProductCommentsActivity::class.java).apply {
                    putExtra(EXTRA_PRODUCT, productExtra)
                    putExtra(EXTRA_TOTAL_COMMENTS_COUNT, totalCommentsCount)
                }
            )
        }
    }

    private val viewModel: ProductCommentsViewModel by viewModels()
    private val totalCommentsCount by lazy { intent.getIntExtra(EXTRA_TOTAL_COMMENTS_COUNT, 0) }
    private val product by lazy { intent.getParcelableExtra<ProductExtra>(EXTRA_PRODUCT)!! }
    private val adapter by lazy {
        ProductCommentsPagingAdapter({ comment ->
            viewModel.updateComment(comment)
        }) {
            CommentManageDialog.newInstance(product, it.reviewText).run {
                show(supportFragmentManager, tag)
            }
        }
    }

    override fun bindingFactory(): ActivityProductCommentsBinding {
        return ActivityProductCommentsBinding.inflate(layoutInflater)
    }

    override fun initViews() = with(binding) {
        ivBackBtn.setOnClickListener {
            finish()
        }
        rvProductComments.adapter = adapter
        srlRefresh.setOnRefreshListener {
            adapter.refresh()
            srlRefresh.isRefreshing = false
        }
    }

    override fun bindViews() {
        lifecycleScope.launch {
            viewModel.getProductComments(totalCommentsCount, product.id).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun onChanged() {
        adapter.refresh()
    }
}
