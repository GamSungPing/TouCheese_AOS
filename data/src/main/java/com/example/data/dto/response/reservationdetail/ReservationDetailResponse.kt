package com.example.data.dto.response.reservationdetail

import com.example.domain.model.ReservationDetail
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ReservationDetailResponse(
    val data: ReservationDetailData,
    val msg: String,
    val statusCode: Int
) {
    fun toDomainModel(): ReservationDetail {
        return ReservationDetail(
            id = data.id,
            studioImg = data.studioImg,
            studioId = data.studioId,
            studioName = data.studioName,
            phoneNumber = data.phoneNumber,
            memberName = data.memberName,
            memberEmail = data.memberEmail,
            reservationDate = data.reservationDate,
            reservationTime = data.reservationTime,
            productName = data.productName,
            productOption = data.productOption,
            totalPrice = data.totalPrice,
            studioAddress = data.studioAddress,
            reservationStatus = data.reservationStatus,
            productImage = data.productImage,
            productPrice = data.productPrice,
            addPeopleCnt = data.addPeopleCnt,
            addPeoplePrice = data.addPeoplePrice,
        )
    }
}