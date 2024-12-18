package com.example.presentation.calendar.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.AvailableTime
import com.example.domain.repository.reservation.ReservationRepository
import com.example.presentation.calendar.vm.model.CalendarState
import com.example.presentation.util.dateFormatter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val reservationRepository: ReservationRepository
) : ViewModel() {

    private var _state = MutableStateFlow(CalendarState.create())
    val state get() = _state.asStateFlow()

    fun load(productId: Int) {
        viewModelScope.launch {
            val times = reservationRepository.getReservationTime(
                productId, dateFormatter(LocalDate.now())
            )
            _state.value = _state.value.copy(time = times)
        }
    }

    fun setSelectedDate(date: LocalDate) {
        _state.value = _state.value.copy(selectedDate = date, isDateChanged = true)
    }

    fun loadAvailableReservationTime(studioId: Int, date: LocalDate) {
        viewModelScope.launch {
            val times = reservationRepository.getReservationTime(
                studioId, dateFormatter(date)
            )
            _state.value = _state.value.copy(time = times)
        }
    }

    fun setSelectedTime(time: AvailableTime) {
        _state.value = _state.value.copy(selectedTime = time)
    }
}