package com.example.presentation.screen.splash.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation.screen.login.LoginScreen
import com.example.presentation.screen.splash.SplashScreen
import com.kakao.sdk.user.UserApiClient

@Composable
fun SplashNavHost(
    apiClient: UserApiClient,
    startDestination: String?,
    onClickClose: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination ?: SplashRoute.Splash.route,
    ) {
        composable(route = SplashRoute.Splash.route) {
            SplashScreen(
                navigateToLogin = {
                    navController.navigate(SplashRoute.Login.route)
                }
            )
        }
        composable(route = SplashRoute.Login.route) {
            LoginScreen(
                onClickClose = { onClickClose() },
                apiClient = apiClient
            )
        }
    }
}