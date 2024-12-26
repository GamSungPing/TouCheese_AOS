package com.example.domain.model

data class NewReservation(
    val studioId: Int,
    val reservationDate: String,
    val reservationTime: String,
    val productId: Int,
    val productOption: Set<ProductOption>,
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
                productOption = emptySet(),
                totalPrice = "",
                phoneNumber = "",
                email = "",
                name = "",
                addPeopleCnt = 0
            )
        }
    }
}