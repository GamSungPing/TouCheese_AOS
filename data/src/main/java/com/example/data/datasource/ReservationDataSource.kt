package com.example.data.datasource

import com.example.data.dto.response.reservation.AvailableReservationResponse
import com.example.data.service.ReservationService
import javax.inject.Inject

class ReservationDataSource @Inject constructor(
    private val reservationService: ReservationService
) {
    suspend fun getReservationTime(studioId: Int, date: String): AvailableReservationResponse {
        return reservationService.getAvailableReservationTime(studioId, date)
    }
}