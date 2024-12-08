package com.example.data.dto.response.reservationdetail

import com.example.domain.model.ReservationDetail
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ReservationDetailResponse(
    val data: Data,
    val msg: String,
    val statusCode: Int
) {
    fun toDomainModel(): ReservationDetail {
        return ReservationDetail(
            id = data.id,
            memberEmail = data.memberEmail,
            memberName = data.memberName,
            phoneNumber = data.phoneNumber,
            productOption = data.productOption,
            reservationDate = data.reservationDate,
            reservationTime = data.reservationTime,
            studioAddress = data.studioAddress,
            studioName = data.studioName,
            totalPrice = data.totalPrice
        )
    }
}