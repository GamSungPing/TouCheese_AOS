package com.example.presentation.studio.sideeffect

sealed interface StudioSideEffect {
    object CloseScreen : StudioSideEffect
    data class NavigateToProductDetail(
        val path: String,
        val description: String,
        val productId: Int
    ) : StudioSideEffect
    data class NavigateToReviewDetail(val reviewId: Int) : StudioSideEffect
}