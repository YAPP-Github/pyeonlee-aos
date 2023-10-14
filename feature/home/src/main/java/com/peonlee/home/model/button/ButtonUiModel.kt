package com.peonlee.home.model.button

import com.peonlee.model.MainHomeListItem
import com.peonlee.model.MainHomeViewType

data class ButtonUiModel(
    override val id: Long,
    override val viewType: MainHomeViewType = MainHomeViewType.BUTTON,
    val buttonType: ButtonType,
    val text: String
) : MainHomeListItem
