package com.example.domain.rule

enum class WeekOfDay(val value: String, val key: Int) {
    MonDay("월", 1),
    TUESDAY("화", 2),
    WEDNESDAY("수", 3),
    THURSDAY("목", 4),
    FRIDAY("금", 5),
    SATURDAY("토", 6),
    SUNDAY("일", 7);

    companion object {
        fun of(key: Int): WeekOfDay {
            return entries.find { it.key == key } ?: throw IllegalArgumentException("Invalid key")
        }
    }
}