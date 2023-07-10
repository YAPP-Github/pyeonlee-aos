package com.peonlee.home.model.divider

import com.peonlee.model.MainHomeListItem
import com.peonlee.model.MainHomeViewType

data class HomeDividerUiModel(
    override val id: Long,
    override val viewType: Enum<MainHomeViewType> = MainHomeViewType.DIVIDER
) : MainHomeListItem
