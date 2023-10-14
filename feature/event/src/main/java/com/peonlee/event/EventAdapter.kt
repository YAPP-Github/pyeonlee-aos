package com.peonlee.event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.peonlee.core.ui.Navigator
import com.peonlee.core.ui.databinding.ListItemCurrentEventBinding
import com.peonlee.core.ui.viewholder.event.EventViewHolder
import com.peonlee.model.event.EventUiModel

class EventAdapter(
    private val navigator: Navigator
) : PagingDataAdapter<EventUiModel, EventViewHolder>(EVENT_DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.onBindView(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            ListItemCurrentEventBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            navigator = navigator
        )
    }

    companion object {
        private val EVENT_DIFF_CALLBACK = object : DiffUtil.ItemCallback<EventUiModel>() {
            override fun areItemsTheSame(oldItem: EventUiModel, newItem: EventUiModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: EventUiModel, newItem: EventUiModel): Boolean {
                return oldItem == newItem
            }
        }

    }
}
