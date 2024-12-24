package com.example.presentation.login.vm

data class LoginState (
    val loginEvent: LoginEvent = LoginEvent.LoginRequired,
)