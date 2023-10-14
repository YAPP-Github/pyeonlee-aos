package com.peonlee.home.adapter.viewholder.button

import com.peonlee.core.ui.Navigator
import com.peonlee.core.ui.viewholder.CommonViewHolder
import com.peonlee.home.databinding.ListItemButtonBinding
import com.peonlee.home.model.button.ButtonType
import com.peonlee.home.model.button.ButtonUiModel

class ButtonViewHolder(
    private val binding: ListItemButtonBinding,
    private val onClickButton: (ButtonType) -> Unit
) : CommonViewHolder<ButtonUiModel>(binding) {
    override fun onBindView(item: ButtonUiModel) {
        binding.btnButton.text = item.text
        binding.btnButton.setOnClickListener {
            onClickButton(item.buttonType)
        }
    }
}
