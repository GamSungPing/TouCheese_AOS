package com.example.domain.rule

enum class Output(val msg: String) {
    REVIEW("리뷰 %s (%s)"),
    WORKDAY("월~금 %s / 매주 %s 휴무"),
    SELECT_RESERVATION_DATE("예약하실 날짜를 선택해주세요"),
    OPTION_OUTPUT("%s (%d원)"),
    BASE_PEOPLE_AMOUNT("%d명"),
    SCHEDULE_MESSAGE("%d년 %d월 %d일 %s시"),
    ORDER("선택상품 주문(₩%s)");

    override fun toString(): String = msg

    companion object {
        fun reviewFormat(count: String, reviewCount: Int): String =
            REVIEW.msg.format(count, reviewCount)

        fun workDayFormat(workTime: String, holidays: List<String>): String {
            val holidayStr = holidays.joinToString(", ")
            return WORKDAY.msg.format(workTime, holidayStr)
        }

        fun optionFormat(name: String, price: Int): String {
            return OPTION_OUTPUT.msg.format(name, price)
        }

        fun orderFormat(payment: String): String {
            return ORDER.msg.format(payment)
        }

        fun basePeopleAmountFormat(basePeopleAmount: Int): String {
            return BASE_PEOPLE_AMOUNT.msg.format(basePeopleAmount)
        }

        fun scheduleFormat(year: Int, month: Int, day: Int, hour: String): String {
            return SCHEDULE_MESSAGE.msg.format(year, month, day, hour)
        }
    }
}