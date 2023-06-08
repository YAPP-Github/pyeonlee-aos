package com.peonlee.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peonlee.domain.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    fun snsLogin(token: String) {
        viewModelScope.launch {
            loginUseCase.login(token)
        }
    }
}
