package com.example.presentation.screen.splash.nav

sealed class SplashRoute(val route: String) {
    data object Splash : SplashRoute("SplashScreen")
    data object Login : SplashRoute("LoginScreen")
}