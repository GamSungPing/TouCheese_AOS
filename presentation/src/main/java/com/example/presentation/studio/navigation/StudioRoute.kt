package com.example.presentation.studio.navigation

enum class StudioRoute(
    val route: String,
) {
    STUDIO(route = "StudioScreen"),
    DETAIL(route = "ProductDetailScreen/{description}/{productId}/{path}");
}