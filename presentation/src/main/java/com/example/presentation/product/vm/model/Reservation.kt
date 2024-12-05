package com.example.presentation.product.vm.model

import com.example.domain.rule.Output
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class Reservation(
    val date: LocalDate?,
    val time: LocalTime?,
) {
    val dateTime
        get(): String {
            return if (date != null && time != null) {
                val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm")
                LocalDateTime.of(date, time).format(formatter)
            } else {
                Output.SELECT_RESERVATION_DATE.toString()
            }
        }

    companion object {
        fun create(): Reservation {
            return Reservation(
                date = null,
                time = null,
            )
        }
    }
}