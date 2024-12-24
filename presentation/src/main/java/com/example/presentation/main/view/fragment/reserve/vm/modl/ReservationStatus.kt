package com.example.presentation.main.view.fragment.reserve.vm.modl

enum class ReservationStatus(val status: String) {
    CONFIRM("예약 확정"),
    CANCEL("예약 취소"),
    WAITING("예약 대기"),
    COMPLETE("완료");

    companion object {
        fun fromString(status: String): ReservationStatus {
            return entries.find { it.name.lowercase() == status.lowercase() } ?: WAITING
        }
    }
}
