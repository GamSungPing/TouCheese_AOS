package com.example.domain.repository

import com.example.domain.model.AvailableReservationTime
import com.example.domain.model.NewReservation

interface ReservationRepository {
    suspend fun getReservationTime(studioId: Int, date: String): AvailableReservationTime

    suspend fun makeReservation(receipt: NewReservation)
}