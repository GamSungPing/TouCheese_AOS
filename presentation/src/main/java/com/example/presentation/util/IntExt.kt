package com.example.presentation.util

import java.text.NumberFormat
import java.util.Locale

fun Int.toKoreanUnit(): String = "${NumberFormat.getNumberInstance(Locale.KOREAN).format(this)}원"