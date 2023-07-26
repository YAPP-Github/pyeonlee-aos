package com.peonlee.user.modify

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peonlee.data.handle
import com.peonlee.data.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModifyUserNicknameViewModel @Inject constructor(
    private val userRepository: UserRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val originalNickname: String? = savedStateHandle[ModifyUserNicknameActivity.USER_NICKNAME]
    val userId: Int? = savedStateHandle[ModifyUserNicknameActivity.USER_ID]

    private val _nickname = MutableStateFlow(originalNickname ?: "")
    val nickname: StateFlow<String> = _nickname.asStateFlow()

    private val _uiEvent = MutableSharedFlow<ModifyUserNicknameUiEvent>()
    val uiEvent: SharedFlow<ModifyUserNicknameUiEvent> = _uiEvent.asSharedFlow()

    fun setNickname(newNickname: String) {
        _nickname.value = newNickname
    }

    fun saveNickname() {
        viewModelScope.launch {
            if (nickname.value.isNullOrBlank()) {
                _uiEvent.emit(ModifyUserNicknameUiEvent.Toast("닉네임을 입력해주세요."))
                return@launch
            }

            userRepository.changeUserNickname(nickname = nickname.value)
                .handle(
                    onSuccess = {
                        _uiEvent.emit(ModifyUserNicknameUiEvent.Success)
                    },
                    onError = {
                        _uiEvent.emit(ModifyUserNicknameUiEvent.Toast("닉네임을 변경하는 중에 문제가 발생했어요."))
                    }
                )
        }
    }

    fun clearNickname() {
        _nickname.value = ""
    }
}

sealed interface ModifyUserNicknameUiEvent {
    data class Toast(val message: String) : ModifyUserNicknameUiEvent
    object Success : ModifyUserNicknameUiEvent
}
