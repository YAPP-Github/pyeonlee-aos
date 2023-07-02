package com.peonlee.user

import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.user.databinding.ActivityUserBinding

class UserActivity : BaseActivity<ActivityUserBinding>() {
    override fun bindingFactory(): ActivityUserBinding {
        return ActivityUserBinding.inflate(layoutInflater)
    }
}
