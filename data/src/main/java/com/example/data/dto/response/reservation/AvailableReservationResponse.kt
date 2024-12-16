package com.example.data.dto.response.reservation

import com.example.domain.model.AvailableReservationTime
import com.example.domain.model.AvailableTime
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AvailableReservationResponse (
    val statusCode: Int,
    val msg: String,
    val data: StudioData
){
    fun toDomainModel(): AvailableReservationTime{
        return AvailableReservationTime(
            availableTimes = data.availableSlots.map { AvailableTime(it) },
            open = AvailableTime(data.openingTime),
            deadline = AvailableTime(data.lastReservationTime)
        )
    }
}