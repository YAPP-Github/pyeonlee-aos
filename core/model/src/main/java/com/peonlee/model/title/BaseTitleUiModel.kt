package com.peonlee.model.title

import com.peonlee.model.MainHomeListItem

/**
 * 각 리스트 를 구별 하는 제목 UI Model
 */
data class BaseTitleUiModel(
    override val id: Long,
    override val viewType: Enum<*>,
    val title: String
) : MainHomeListItem
