package com.example.data.service

import com.example.data.dto.response.reservation.ReservationResponse
import com.example.data.dto.response.reservationdetail.ReservationDetailResponse
import retrofit2.http.DELETE
import com.example.data.dto.request.reservation.ReservationRequest
import com.example.data.dto.response.reservation.AvailableReservationResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

internal interface ReservationService {

    @GET("reservation/member/{memberId}")
    suspend fun getReservationsByMemberId(@Path("memberId") memberId: Int) : ReservationResponse

    @GET("reservation/{reservationId}")
    suspend fun getReservationDetailByReservationId(@Path("reservationId") reservationId: Int) : ReservationDetailResponse

    @GET("reservation/member/{memberId}/completed-cancelled")
    suspend fun getCompletedReservationByMemberId(@Path("memberId") memberId: Int) : ReservationResponse

    @DELETE("reservation/{reservationId}/cancel")
    suspend fun deleteReservationByReservationId(
        @Path("reservationId") reservationId: Int,
        @Query("memberId") memberId: Int
    )

    @GET("reservation/{studioId}/available-slots")
    suspend fun getAvailableReservationTime(
        @Path("studioId") studioId: Int,
        @Query("date") date: String
    ): AvailableReservationResponse

    @POST("reservation")
    suspend fun makeReservation(
        @Body request: ReservationRequest
    )
}