package com.example.data.datasource

import com.example.data.dto.request.reservation.ReservationRequest
import com.example.data.dto.response.reservation.AvailableReservationResponse
import com.example.data.dto.response.reservation.ReservationResponse
import com.example.data.dto.response.reservationdetail.ReservationDetailResponse
import com.example.data.service.ReservationService
import javax.inject.Inject

internal class ReservationDataSource @Inject constructor(
    private val reservationService: ReservationService
) {
    suspend fun getReservationsByMemberId(memberId: Long): ReservationResponse {
        return reservationService.getReservationsByMemberId(memberId)
    }

    suspend fun getReservationDetailByReservationId(reservationId: Int): ReservationDetailResponse {
        return reservationService.getReservationDetailByReservationId(reservationId)
    }

    suspend fun getCompletedReservationByMemberId(memberId: Long): ReservationResponse {
        return reservationService.getCompletedReservationByMemberId(memberId)
    }

    suspend fun deleteReservationByReservationId(reservationId: Int, memberId: Long) {
        reservationService.deleteReservationByReservationId(reservationId, memberId)
    }

    suspend fun getReservationTime(studioId: Int, date: String): AvailableReservationResponse {
        return reservationService.getAvailableReservationTime(studioId, date)
    }

    suspend fun makeReservation(request: ReservationRequest){
        reservationService.makeReservation(request)
    }
}