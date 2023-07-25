package com.peonlee.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peonlee.data.Result
import com.peonlee.data.local.LocalDataSource
import com.peonlee.data.model.login.AuthRequest
import com.peonlee.data.model.login.AuthResult
import com.peonlee.domain.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val dataStore: LocalDataSource
) : ViewModel() {

    var loginType: String = ""
        private set

    var loginToken: String = ""
        private set

    private val _loginState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Init)
    val loginState = _loginState.asStateFlow()

    fun login(token: String, type: String) {
        loginType = type
        loginToken = token

        viewModelScope.launch {
            val loginResult = loginUseCase.login(token, setRequest(token, type))
            handleState(loginResult)
        }
    }

    fun signUp() {
        viewModelScope.launch {
            val signUpResult = loginUseCase.signUp(loginToken, setRequest(loginToken, loginType))
            handleState(signUpResult)
        }
    }

    fun setToken(accessToken: String) {
        viewModelScope.launch {
            dataStore.setAccessToken(accessToken)
        }
    }

    fun getToken(): Flow<String> = dataStore.getAccessToken()

    private fun handleState(result: Result<AuthResult>) {
        viewModelScope.launch {
            when (result) {
                is Result.Success -> _loginState.emit(LoginState.Success(result.data))

                is Result.Error -> {
                    when (result.exception.message?.contains("status") ?: false) { // TODO : 리팩토링 data layer에서 파싱
                        true -> _loginState.emit(LoginState.Fail)
                        false -> _loginState.emit(LoginState.NotRegistered)
                    }
                }
            }
        }
    }

    private fun setRequest(token: String, type: String): AuthRequest = AuthRequest(token, type)
}

sealed class LoginState {
    object Init : LoginState()
    data class Success(val data: AuthResult) : LoginState()
    object NotRegistered : LoginState()
    object Fail : LoginState()
}
