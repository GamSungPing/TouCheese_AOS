package com.example.presentation.login.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()

    init {
        initializeLoginState()
    }

    private fun initializeLoginState() {
        viewModelScope.launch {
            authRepository.loggedIn
                .map { isLoggedIn ->
                    LoginState(
                        loginEvent = if (isLoggedIn) LoginEvent.LoggedIn else LoginEvent.LoginRequired
                    )
                }
                .onEach { newState ->
                    _uiState.value = newState
                }
                .launchIn(viewModelScope)
        }
    }


    fun login(token: String) {
        viewModelScope.launch {
            authRepository.login(token).onSuccess {
                _uiState.value = _uiState.value.copy(
                    loginEvent = LoginEvent.Success
                )
            }.onFailure {
                _uiState.value = _uiState.value.copy(
                    loginEvent = LoginEvent.Error
                )
            }
        }
    }
}