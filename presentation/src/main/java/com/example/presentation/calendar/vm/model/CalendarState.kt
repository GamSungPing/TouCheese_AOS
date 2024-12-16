package com.example.presentation.calendar.vm.model

import com.example.domain.model.AvailableReservationTime
import com.example.domain.model.AvailableTime
import java.time.LocalDate

data class CalendarState(
    val startYear: Int,
    val endYear: Int,
    val selectedDate: LocalDate,
    val selectedTime: AvailableTime?,
    val isDateChanged: Boolean,
    val time: AvailableReservationTime
) {

    val initialPage
        get() = (selectedDate.year - startYear) * 12 + selectedDate.monthValue - 1

    val pageCount = (endYear - startYear) * 12

    companion object{
        fun create(): CalendarState{
            val now = LocalDate.now()
            return CalendarState(
                startYear = now.year,
                endYear = now.year + 2,
                selectedDate = now,
                selectedTime = null,
                isDateChanged = false,
                time = AvailableReservationTime.create()
            )
        }
    }
}