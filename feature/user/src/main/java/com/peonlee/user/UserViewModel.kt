package com.peonlee.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peonlee.data.handle
import com.peonlee.data.user.UserRepository
import com.peonlee.user.model.UserUiModel
import com.peonlee.user.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _user = MutableStateFlow(UserUiModel())
    val user: StateFlow<UserUiModel> = _user.asStateFlow()

    init {
        viewModelScope.launch {
            userRepository.getUserInfo().handle(
                onSuccess = {
                    _user.value = it.toUiModel()
                }
            )
        }
    }
}
