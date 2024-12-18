package com.example.domain.model

data class NewReservation(
    val memberId: Int = 1,
    val studioId: Int,
    val reservationDate: String,
    val reservationTime: String,
    val productId: Int,
    val productOption: List<ProductOption>,
    val totalPrice: String,
    val addPeopleCnt: Int,
    val email: String,
    val phoneNumber: String,
    val name: String,
){
    companion object{
        fun create(): NewReservation{
            return NewReservation(
                studioId = 0,
                reservationDate = "",
                reservationTime = "",
                productId = 0,
                productOption = emptyList(),
                totalPrice = "",
                phoneNumber = "",
                email = "",
                name = "",
                addPeopleCnt = 0
            )
        }
    }
}