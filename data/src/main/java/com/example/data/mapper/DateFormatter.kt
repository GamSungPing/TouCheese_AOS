package com.example.data.mapper

import com.example.domain.rule.Time.Companion.workTimeFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter

internal fun formatOperatingHours(openTime: String, closeTime: String): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    val openSpliterator = openTime.split(":").map { it.toInt() }
    val closeSpliterator = closeTime.split(":").map { it.toInt() }

    val open = LocalTime.of(openSpliterator[0], openSpliterator[1]).format(formatter)
    val close = LocalTime.of(closeSpliterator[0], closeSpliterator[1]).format(formatter)

    return workTimeFormat(open, close)
}