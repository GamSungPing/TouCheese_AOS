package com.example.data.service

import com.example.data.dto.response.reservation.AvailableReservationResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReservationService {
    @GET("reservation/{studioId}/available-slots")
    suspend fun getAvailableReservationTime(
        @Path("studioId") studioId: Int,
        @Query("date") date: String
    ): AvailableReservationResponse
}