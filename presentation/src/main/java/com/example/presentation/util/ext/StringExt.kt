package com.example.presentation.util.ext

import android.util.Patterns

fun removeSecondsFromTime(time: String): String {
    return time.substringBeforeLast(":")
}

fun String.emailFormatValidation(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.phoneNumberFormatValidation(): Boolean = Patterns.PHONE.matcher(this).matches()

fun String.nameFormatValidation(): Boolean = this.matches(Regex("^[가-힣]{2,5}\$"))