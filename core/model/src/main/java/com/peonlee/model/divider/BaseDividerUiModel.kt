package com.peonlee.model.divider

import com.peonlee.model.MainHomeListItem

data class BaseDividerUiModel(
    override val id: Long,
    override val viewType: Enum<*>
) : MainHomeListItem
