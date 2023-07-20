package com.peonlee.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peonlee.data.Result
import com.peonlee.data.local.LocalDataSource
import com.peonlee.data.model.login.AuthRequest
import com.peonlee.data.model.login.AuthResult
import com.peonlee.domain.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val dataStore: LocalDataSource
) : ViewModel() {

    private val _loginState: MutableSharedFlow<LoginState> = MutableStateFlow(LoginState.Init)
    val loginState = _loginState.asSharedFlow()

    fun login(token: String, type: String) {
        viewModelScope.launch {
            val loginResult = loginUseCase.login(token, setRequest(token, type))
            handleState(loginResult)
        }
    }

    fun signUp(token: String, type: String) {
        viewModelScope.launch {
            val signUpResult = loginUseCase.signUp(token, setRequest(token, type))
            handleState(signUpResult)
        }
    }

    fun setToken(accessToken: String) {
        viewModelScope.launch {
            dataStore.setAccessToken(accessToken)
        }
    }

    fun getToken() : Flow<String> = dataStore.getAccessToken()

    private fun handleState(result: Result<AuthResult>) {
        viewModelScope.launch {
            when(result) {
                is Result.Success -> _loginState.emit(LoginState.Success(result.data))
                is Result.Error -> {
                    when(result.exception.message?.contains("status") ?: false) { // TODO : 리팩토링 data layer에서 파싱
                        true -> _loginState.emit(LoginState.Fail)
                        false -> _loginState.emit(LoginState.Already)
                    }
                }
            }
        }
    }

    private fun setRequest(token: String, type: String) : AuthRequest = AuthRequest(token, type)
}

sealed class LoginState {
    object Init : LoginState()
    data class Success(val data: AuthResult) : LoginState()
    object Already : LoginState()
    object Fail : LoginState()
}
