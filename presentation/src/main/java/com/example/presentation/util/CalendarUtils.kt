package com.example.presentation.util

import com.example.presentation.util.ext.addPadding
import java.time.LocalDate
import java.util.Calendar

fun getWeeksOfMonth(year: Int, month: Int, day: Int): Int {
    val calendar = Calendar.getInstance()
    calendar.set(year, month - 1, day)
    return calendar.get(Calendar.WEEK_OF_MONTH)
}

fun toDateString(month: Int, day: Int, dayOfWeeks: String): String {
    return "${month.toString().padStart(2, '0')}월 ${
        day.toString().padStart(2, '0')
    }일 (${dayOfWeeks})"
}

fun dateFormatter(date: LocalDate): String {
    val year = date.year
    val month = date.monthValue
    val day = date.dayOfMonth
    return "$year-${month.addPadding()}-${day.addPadding()}"
}

fun toCalendarTitle(year: Int, month: Int): String {
    return "${year}년 ${month.toString().padStart(2, '0')}월"
}