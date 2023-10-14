package com.peonlee.event

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.peonlee.core.ui.Navigator
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.event.databinding.ActivityEventBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class EventActivity : BaseActivity<ActivityEventBinding>() {
    @Inject
    lateinit var navigator: Navigator
    private val eventViewModel: EventViewModel by viewModels()

    private val eventAdapter: EventAdapter by lazy { EventAdapter(navigator) }

    override fun bindingFactory(): ActivityEventBinding {
        return ActivityEventBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()
        binding.rcvEvent.setHasFixedSize(true)
        binding.rcvEvent.adapter = eventAdapter

        eventViewModel.events.flowWithLifecycle(lifecycle)
            .onEach { eventAdapter.submitData(it) }
            .launchIn(lifecycleScope)
    }

    override fun bindViews() {
        super.bindViews()
        binding.btnBack.setOnClickListener { finish() }
    }

    companion object {
        fun start(
            context: Context
        ) {
            context.startActivity(Intent(context, EventActivity::class.java))
        }
    }
}
