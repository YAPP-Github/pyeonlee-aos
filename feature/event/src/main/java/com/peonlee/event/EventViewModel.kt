package com.peonlee.event

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.peonlee.model.event.EVENT_DUMMY
import com.peonlee.model.event.EventUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor() : ViewModel() {

    val events: Flow<PagingData<EventUiModel>> = getEventsFlow()

    private fun getEventsFlow(): Flow<PagingData<EventUiModel>> {
        return flowOf(PagingData.from(EVENT_DUMMY))
    }
}
