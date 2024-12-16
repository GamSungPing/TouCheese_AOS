package com.example.presentation.studio.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation.studio.screen.ProductDetailScreen
import com.example.presentation.studio.screen.StudioScreen

@Composable
fun StudioNavHost(
    studioId: String,
    profileURL: String,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = StudioRoute.STUDIO.route
    ) {
        composable(route = StudioRoute.STUDIO.route) {
            StudioScreen(studioId, profileURL, navigateToDetail = { description, productId, path ->
                navController.setProductDefaultInfo(description, productId, path)
                navController.navigate(StudioRoute.DETAIL.route)
                }
            )
        }

        composable(route = StudioRoute.DETAIL.route) {
            ProductDetailScreen(
                description = navController.getDescription(),
                productId = navController.getProductId(),
                path = navController.getProfileUrl(),
                navController
            )
        }
    }
}