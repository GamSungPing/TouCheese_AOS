package com.example.domain.repository.reservation

import com.example.domain.model.AvailableReservationTime
import com.example.domain.model.NewReservation
import com.example.domain.model.Reservation
import com.example.domain.model.ReservationDetail

interface ReservationRepository {
    suspend fun getReservationsByMemberId(memberId: Long): List<Reservation>
    suspend fun getReservationDetailByReservationId(reservationId: Int): ReservationDetail
    suspend fun getCompletedReservationByMemberId(memberId: Long): List<Reservation>
    suspend fun deleteReservationByReservationId(reservationId: Int, memberId: Long)
    suspend fun getReservationTime(studioId: Int, date: String): AvailableReservationTime
    suspend fun makeReservation(receipt: NewReservation)
}