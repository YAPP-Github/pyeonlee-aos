package com.peonlee.home.model.divider

import com.peonlee.model.MainHomeListItem

data class DividerUiModel(
    override val id: Long,
    override val viewType: Enum<*>
) : MainHomeListItem
