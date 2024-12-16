package com.example.presentation.util.ext

fun removeSecondsFromTime(time: String): String {
    return time.substringBeforeLast(":")
}