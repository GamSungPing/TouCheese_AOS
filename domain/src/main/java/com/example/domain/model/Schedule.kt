package com.example.domain.model

import com.example.domain.rule.Output.Companion.scheduleFormat
import java.time.LocalDate

data class Schedule (
    val date: LocalDate,
    val time: AvailableTime,
){
    val scheduleMessage
        get() = scheduleFormat(
            date.year,
            date.monthValue,
            date.dayOfMonth,
            time.value.substring(0, 2)
        )
}