package com.example.domain.rule

enum class Time(val value: String) {
    WORK_TIME("%s ~ %s");

    override fun toString(): String = value

    companion object {
        fun workTimeFormat(open: String, close: String): String {
            return WORK_TIME.value.format(open, close)
        }
    }
}