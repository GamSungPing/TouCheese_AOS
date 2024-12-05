package com.example.presentation.product.vm.model

import com.example.domain.model.ProductDetail
import com.example.presentation.util.toKoreanUnit

data class ProductState (
    val product: ProductDetail,
    val reservation: Reservation
){
    val productPrice get() = product.productPrice.toKoreanUnit()

    companion object{
        fun create(): ProductState{
            return ProductState(
                product = ProductDetail.create(),
                reservation = Reservation.create()
            )
        }
    }
}