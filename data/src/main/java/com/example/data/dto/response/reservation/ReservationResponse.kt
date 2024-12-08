package com.example.data.dto.response.reservation

import com.example.domain.model.Reservation
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ReservationResponse(
    val data: List<Data>,
    val msg: String,
    val statusCode: Int
) {
    fun toDomainModel() : List<Reservation> {
        return data.map {
            Reservation(
                id = it.id,
                reservationDate = it.reservationDate,
                reservationStatus = it.reservationStatus,
                reservationTime = it.reservationTime,
                studioImage = it.studioImage,
                studioName = it.studioName
            )
        }
    }
}