package com.example.data.service

import com.example.data.dto.response.reservation.ReservationResponse
import com.example.data.dto.response.reservationdetail.ReservationDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface ReservationService {

    @GET("reservation/member/{memberId}")
    suspend fun getReservationsByMemberId(@Path("memberId") memberId: Int) : ReservationResponse

    @GET("reservation/{reservationId}")
    suspend fun getReservationDetailByReservationId(@Path("reservationId") reservationId: Int) : ReservationDetailResponse

    @GET("reservation/member/{memberId}/completed")
    suspend fun getCompletedReservationByMemberId(@Path("memberId") memberId: Int) : ReservationResponse
}