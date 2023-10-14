package com.peonlee.core.ui.viewholder.event

import coil.load
import com.peonlee.common.ext.format
import com.peonlee.core.ui.Navigator
import com.peonlee.core.ui.databinding.ListItemCurrentEventBinding
import com.peonlee.core.ui.extensions.getStringWithArgs
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.model.event.EventUiModel
import com.peonlee.model.type.StoreType
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import com.peonlee.core.ui.R

/**
 * 홈 화면 event item
 */
class EventViewHolder(
    private val binding: ListItemCurrentEventBinding,
    private val navigator: Navigator
) : CommonViewHolder<EventUiModel>(binding) {
    override fun onBindView(item: EventUiModel) {
        with(binding) {
            txtStore.text = item.store.storeName
            imgStore.setImageResource(getIconByStore(item.store))

            // 이벤트 정보
            imgEvent.load(item.imageUrl)
            txtEvent.text = item.title
            txtDuration.text = getStringWithArgs(
                R.string.item_event_duration,
                item.startedDate.format("."),
                item.endedDate.format(".")
            )
            val extraDays = ChronoUnit.DAYS.between(LocalDate.now(), item.endedDate)
            txtExtra.text = getStringWithArgs(
                R.string.item_event_extra,
                when {
                    extraDays < 0L -> "종료"
                    extraDays == 0L -> "Day"
                    else -> extraDays.toString()
                }
            )
        }
    }

    companion object {
        private fun getIconByStore(storeType: StoreType): Int = when (storeType) {
            StoreType.CU -> R.drawable.ic_store_cu
            StoreType.GS25 -> R.drawable.ic_store_gs25
            StoreType.SEVEN -> R.drawable.ic_store_seveneleven
        }
    }
}
