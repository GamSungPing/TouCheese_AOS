package com.example.presentation.screen.login.vm

sealed interface LoginEvent {
    data object LoginRequired : LoginEvent
    data object LoggedIn : LoginEvent
    data object Success : LoginEvent
    data object Error : LoginEvent
}