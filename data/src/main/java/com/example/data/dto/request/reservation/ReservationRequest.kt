package com.example.data.dto.request.reservation

import com.example.domain.model.NewReservation

data class ReservationRequest(
    val memberId: Long,
    val studioId: Int,
    val reservationDate: String,
    val reservationTime: String,
    val productId: Int,
    val productOption: String,
    val totalPrice: Int,
    val phoneNumber: String,
    val email: String,
    val addPeopleCnt: Int
)

internal fun NewReservation.toRequestModel(memberId: Long): ReservationRequest{
    val options = productOption.joinToString("@") { "${it.name}:${it.price}" }
    val payment = totalPrice.filter { it.isDigit() }.toInt()

    return ReservationRequest(
        memberId = memberId,
        studioId = studioId,
        reservationDate = reservationDate,
        reservationTime = reservationTime,
        productId = productId,
        productOption = options,
        totalPrice = payment,
        phoneNumber = phoneNumber,
        email = email,
        addPeopleCnt = addPeopleCnt
    )
}