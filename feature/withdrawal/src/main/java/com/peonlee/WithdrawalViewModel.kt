package com.peonlee

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peonlee.data.Result
import com.peonlee.data.local.LocalDataSource
import com.peonlee.data.model.user.DeleteRequest
import com.peonlee.domain.user.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WithdrawalViewModel @Inject constructor(
    private val datastore: LocalDataSource,
    private val userUseCase: UserUseCase
) : ViewModel() {
    private val _withdrawalStateFlow: MutableStateFlow<WithdrawalUiState> = MutableStateFlow(WithdrawalUiState.Init)
    val withdrawalStateFlow: StateFlow<WithdrawalUiState> = _withdrawalStateFlow.asStateFlow()

    fun deleteUser(memberId: Int) {
        viewModelScope.launch {
            when (userUseCase.deleteUser(DeleteRequest(memberId))) {
                is Result.Success -> _withdrawalStateFlow.emit(WithdrawalUiState.Success)
                is Result.Error -> { _withdrawalStateFlow.emit(WithdrawalUiState.Fail) }
            }
        }
    }

    fun setAccessToken() {
        viewModelScope.launch {
            datastore.setAccessToken("")
        }
    }
}

sealed class WithdrawalUiState {
    object Init : WithdrawalUiState()
    object Success : WithdrawalUiState()
    object Fail : WithdrawalUiState()
}
