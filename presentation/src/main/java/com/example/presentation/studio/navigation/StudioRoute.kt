package com.example.presentation.studio.navigation

sealed class StudioRoute(val route: String) {
    data object Studio : StudioRoute("StudioScreen")
    data object Detail : StudioRoute("ProductDetailScreen")
    data object Reserve : StudioRoute("ReserveScreen")
    data object ReserveComplete : StudioRoute("ReserveCompleteScreen")
}
