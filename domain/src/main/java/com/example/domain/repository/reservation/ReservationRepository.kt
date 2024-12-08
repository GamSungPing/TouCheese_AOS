package com.example.domain.repository.reservation

import com.example.domain.model.Reservation
import com.example.domain.model.ReservationDetail

interface ReservationRepository {
    suspend fun getReservationsByMemberId(memberId: Int) : List<Reservation>
    suspend fun getReservationDetailByReservationId(reservationId: Int) : ReservationDetail
}