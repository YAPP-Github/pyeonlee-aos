package com.peonlee.storage

import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.storage.databinding.ActivityStorageBinding

class StorageActivity : BaseActivity<ActivityStorageBinding>() {
    override fun bindingFactory() = ActivityStorageBinding.inflate(layoutInflater)

    override fun initViews() {
    }
}
