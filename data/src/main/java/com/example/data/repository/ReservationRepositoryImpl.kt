package com.example.data.repository

import com.example.data.datasource.ReservationDataSource
import com.example.data.dto.request.reservation.toRequestModel
import com.example.domain.model.AvailableReservationTime
import com.example.domain.model.NewReservation
import com.example.domain.model.ReservationDetail
import com.example.domain.model.Reservation
import com.example.domain.repository.reservation.ReservationRepository
import javax.inject.Inject

internal class ReservationRepositoryImpl @Inject constructor(
    private val reservationDataSource: ReservationDataSource
) : ReservationRepository {

    override suspend fun getReservationsByMemberId(memberId: Int): List<Reservation>  {
        return reservationDataSource.getReservationsByMemberId(memberId).toDomainModel()
    }

    override suspend fun getReservationDetailByReservationId(reservationId: Int): ReservationDetail {
        return reservationDataSource.getReservationDetailByReservationId(reservationId).toDomainModel()
    }

    override suspend fun getCompletedReservationByMemberId(memberId: Int): List<Reservation> {
        return reservationDataSource.getCompletedReservationByMemberId(memberId).toDomainModel()
    }

    override suspend fun deleteReservationByReservationId(reservationId: Int, memberId: Int) {
        return reservationDataSource.deleteReservationByReservationId(reservationId, memberId)
    }

    override suspend fun getReservationTime(studioId: Int, date: String): AvailableReservationTime {
        return reservationDataSource.getReservationTime(studioId, date).toDomainModel()
    }

    override suspend fun makeReservation(receipt: NewReservation) {
        reservationDataSource.makeReservation(receipt.toRequestModel())
    }
}