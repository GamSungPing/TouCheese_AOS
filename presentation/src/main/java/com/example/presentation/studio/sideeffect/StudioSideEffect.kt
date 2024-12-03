package com.example.presentation.studio.sideeffect

sealed interface StudioSideEffect {
    object CloseScreen : StudioSideEffect
    data class NavigateToReservation(val message: String) : StudioSideEffect
}