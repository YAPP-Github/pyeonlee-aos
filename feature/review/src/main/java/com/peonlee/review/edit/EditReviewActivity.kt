package com.peonlee.review.edit

import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.review.databinding.ActivityEditReviewBinding

class EditReviewActivity : BaseActivity<ActivityEditReviewBinding>() {
    override fun bindingFactory(): ActivityEditReviewBinding {
        return ActivityEditReviewBinding.inflate(layoutInflater)
    }
}
