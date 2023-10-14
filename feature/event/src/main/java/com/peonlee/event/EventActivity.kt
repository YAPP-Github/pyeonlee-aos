package com.peonlee.event

import android.content.Context
import android.content.Intent
import com.peonlee.core.ui.Navigator
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.event.databinding.ActivityEventBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EventActivity : BaseActivity<ActivityEventBinding>() {
    @Inject
    lateinit var navigator: Navigator

    private val eventAdapter: EventAdapter by lazy { EventAdapter(navigator) }

    override fun bindingFactory(): ActivityEventBinding {
        return ActivityEventBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()
        binding.rcvEvent.setHasFixedSize(true)
        binding.rcvEvent.adapter = eventAdapter
    }

    companion object {
        fun start(
            context: Context
        ) {
            context.startActivity(Intent(context, EventActivity::class.java))
        }
    }
}
