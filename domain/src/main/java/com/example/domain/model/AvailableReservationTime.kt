package com.example.domain.model

data class AvailableReservationTime (
    val availableTimes: List<AvailableTime>,
    val open: AvailableTime,
    val deadline: AvailableTime
){
    val morningSlots get() = availableTimes.filter { it.value.substring(0, 2).toInt() in 0..11 }
    val afternoonSlots get() = availableTimes.filter { it.value.substring(0, 2).toInt() in 12..23 }

    companion object{
        fun create(): AvailableReservationTime{
            return AvailableReservationTime(
                listOf(
                    AvailableTime("09:00"),
                    AvailableTime("10:00"),
                    AvailableTime("11:00"),
                    AvailableTime("12:00"),
                    AvailableTime("13:00"),
                    AvailableTime("15:00"),
                    AvailableTime("16:00"),
                    AvailableTime("17:00"),
                    AvailableTime("18:00"),
                    AvailableTime("19:00"),
                ),
                AvailableTime(""),
                AvailableTime("")
            )
        }
    }
}

@JvmInline
value class AvailableTime(
    val value: String
)