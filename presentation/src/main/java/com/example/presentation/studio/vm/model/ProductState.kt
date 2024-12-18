package com.example.presentation.studio.vm.model

import com.example.domain.model.AvailableReservationTime
import com.example.domain.model.ProductDetail
import com.example.domain.model.ProductOption
import com.example.domain.model.Schedule
import com.example.domain.rule.Output.Companion.orderFormat
import com.example.presentation.util.ext.toKoreanUnit

data class ProductState(
    val product: ProductDetail,
    val addGuestCount: Int,
    val selectedOption: List<ProductOption>,
    val availableReservationTime: AvailableReservationTime,
    val schedule: Schedule?
) {
    val productPriceMessage get() = product.productPrice.toKoreanUnit()

    val payment
        get() = (product.productPrice +
                selectedOption.sumOf { it.price } +
                (product.addPeoplePrice * addGuestCount))

    val paymentMessage get() = orderFormat(payment.toKoreanUnit())

    val scheduleMessage
        get() = schedule?.scheduleMessage ?: "예약 일자 및 시간 선택"

    companion object {
        fun create(): ProductState {
            return ProductState(
                product = ProductDetail.create(),
                addGuestCount = 0,
                emptyList(),
                availableReservationTime = AvailableReservationTime.create(),
                schedule = null
            )
        }
    }
}