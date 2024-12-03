package com.example.domain.rule

enum class Output(val msg: String) {
    REVIEW("리뷰 %s (%s)"),
    WORKDAY("월~금 %s / 매주 %s 휴무");

    override fun toString(): String = msg

    companion object {
        fun reviewFormat(count: String, reviewCount: Int): String =
            REVIEW.msg.format(count, reviewCount)

        fun workDayFormat(workTime: String, holidays: List<String>): String {
            val holidayStr = holidays.joinToString(", ")
            return WORKDAY.msg.format(workTime, holidayStr)
        }
    }
}