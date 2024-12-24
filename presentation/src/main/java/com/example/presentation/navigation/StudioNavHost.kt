package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation.navigation.parcelable.getProductInfoParcelable
import com.example.presentation.navigation.parcelable.getReservationParcelable
import com.example.presentation.navigation.parcelable.setProductInfoParcelable
import com.example.presentation.navigation.parcelable.setReservationParcelable
import com.example.presentation.screen.product_detail.ProductDetailScreen
import com.example.presentation.screen.reservation.ReservationCompleteScreen
import com.example.presentation.screen.reservation.ReserveScreen
import com.example.presentation.screen.studio.StudioScreen

@Composable
fun StudioNavHost(
    studioId: String,
    profileURL: String,
    onClickBack: () -> Unit,
    navigateToSchedule: () -> Unit,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = StudioRoute.Studio.route
    ) {
        composable(route = StudioRoute.Studio.route) {
            StudioScreen(
                studioId,
                profileURL,
                navigateToBackStack = { onClickBack() },
                navigateToDetail = { bundle ->
                    navController.setProductInfoParcelable(bundle)
                    navController.navigate(StudioRoute.Detail.route)
                }
            )
        }

        composable(route = StudioRoute.Detail.route) {
            ProductDetailScreen(
                bundle = navController.getProductInfoParcelable(),
                navigateToBackStack = {
                    navController.popBackStack()
                },
                navigateToReserve = { bundle ->
                    navController.setReservationParcelable(bundle)
                    navController.navigate(StudioRoute.Reserve.route)
                }
            )
        }

        composable(route = StudioRoute.Reserve.route) {
            ReserveScreen(
                bundle = navController.getReservationParcelable(),
                navigateToBackStack = {
                    navController.popBackStack()
                },
                navigateToReserveComplete = {
                    navController.navigate(StudioRoute.ReserveComplete.route){
                        this.launchSingleTop = true
                    }
                }
            )
        }

        composable(route = StudioRoute.ReserveComplete.route) {
            ReservationCompleteScreen(
                onCheckScheduleClick = { navigateToSchedule() },
                onConfirmClick = {
                    navController.navigate(StudioRoute.Studio.route){
                        this.launchSingleTop = true
                    }
                }
            )
        }
    }
}