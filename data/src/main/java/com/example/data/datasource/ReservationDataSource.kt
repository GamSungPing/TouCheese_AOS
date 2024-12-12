package com.example.data.datasource

import com.example.data.service.ReservationService
import com.example.domain.model.ReservationDetail
import com.example.domain.model.Reservation
import javax.inject.Inject

internal data class ReservationDataSource @Inject constructor(
    private val reservationService: ReservationService
) {
    suspend fun getReservationsByMemberId(memberId: Int): List<Reservation> {
        return reservationService.getReservationsByMemberId(memberId).toDomainModel()
    }

    suspend fun getReservationDetailByReservationId(reservationId: Int) : ReservationDetail {
        return reservationService.getReservationDetailByReservationId(reservationId).toDomainModel()
    }

    suspend fun getCompletedReservationByMemberId(memberId: Int) : List<Reservation> {
        return reservationService.getCompletedReservationByMemberId(memberId).toDomainModel()
    }

    suspend fun deleteReservationByReservationId(reservationId: Int, memberId: Int) {
        return reservationService.deleteReservationByReservationId(reservationId, memberId)
    }
}