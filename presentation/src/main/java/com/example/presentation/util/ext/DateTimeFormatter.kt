package com.example.presentation.util.ext

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Long.convertMillisToDate(): String {
    val localDate = Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    localDate.format(formatter)

    return localDate.toString()
}