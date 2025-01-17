package com.example.presentation.util.ext

import java.text.NumberFormat
import java.util.Locale

fun Int.toKoreanUnit(): String = "${NumberFormat.getNumberInstance(Locale.KOREAN).format(this)}원"

fun Int.addPadding(): String = this.toString().padStart(2, '0')