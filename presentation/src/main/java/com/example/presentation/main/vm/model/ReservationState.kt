package com.example.presentation.main.vm.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class ReservationState(
    val date : LocalDate? = null,
    val time : LocalTime? = null,
    val hasSelectedDate: Boolean = false,
    val dateTime : LocalDateTime? = null
)