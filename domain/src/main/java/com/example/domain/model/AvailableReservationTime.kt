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
                listOf(),
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