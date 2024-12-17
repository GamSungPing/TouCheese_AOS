package com.example.data.repository

import com.example.data.datasource.ReservationDataSource
import com.example.domain.model.ReservationDetail
import com.example.domain.model.Reservation
import com.example.domain.repository.reservation.ReservationRepository
import javax.inject.Inject

internal class ReservationRepositoryImpl @Inject constructor(
    private val reservationDataSource: ReservationDataSource
) : ReservationRepository {
    override suspend fun getReservationsByMemberId(memberId: Int): List<Reservation>  {
        return reservationDataSource.getReservationsByMemberId(memberId)
    }

    override suspend fun getReservationDetailByReservationId(reservationId: Int): ReservationDetail {
        return reservationDataSource.getReservationDetailByReservationId(reservationId)
    }

    override suspend fun getCompletedReservationByMemberId(memberId: Int): List<Reservation> {
        return reservationDataSource.getCompletedReservationByMemberId(memberId)
    }

    override suspend fun deleteReservationByReservationId(reservationId: Int, memberId: Int) {
        return reservationDataSource.deleteReservationByReservationId(reservationId, memberId)
    }
}