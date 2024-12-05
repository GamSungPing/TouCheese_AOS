package com.example.domain.rule

enum class Output(val msg: String) {
    REVIEW("리뷰 %s (%s)"),
    WORKDAY("월~금 %s / 매주 %s 휴무"),
    SELECT_RESERVATION_DATE("예약하실 날짜를 선택해주세요");

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