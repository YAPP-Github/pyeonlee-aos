package com.peonlee.event

import android.content.Context
import android.content.Intent
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.event.databinding.ActivityEventBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventActivity : BaseActivity<ActivityEventBinding>() {
    override fun bindingFactory(): ActivityEventBinding {
        return ActivityEventBinding.inflate(layoutInflater)
    }

    companion object {
        fun start(
            context: Context
        ) {
            context.startActivity(Intent(context, EventActivity::class.java))
        }
    }
}
