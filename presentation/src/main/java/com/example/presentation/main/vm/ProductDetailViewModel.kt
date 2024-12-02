package com.example.presentation.main.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.presentation.main.vm.model.ReservationState
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ProductDetailViewModel : ViewModel() {

    private val _reservationState = MutableLiveData(ReservationState())
    val reservationState: LiveData<ReservationState> get() = _reservationState

    private val _isOrderEnabled = MutableLiveData<Boolean>()
    val isOrderEnabled: LiveData<Boolean> get() = _isOrderEnabled

    fun setButtonEnabled(enabled: Boolean) {
        viewModelScope.launch {
            _isOrderEnabled.value = enabled
        }
    }

    fun setSelectedDay(date: LocalDate) {
        viewModelScope.launch {
            _reservationState.value = _reservationState.value?.copy(date = date)
        }
    }

    fun setSelectedTime(time : LocalTime) {
        viewModelScope.launch {
            _reservationState.value = _reservationState.value?.copy(time = time)
        }
    }

    fun setHasSelectDateTime(boolean: Boolean) {
        viewModelScope.launch {
            _reservationState.value = _reservationState.value?.copy(hasSelectedDate = boolean)
        }
    }

    fun setSelectedDateTime() {
        val date = _reservationState.value?.date
        val time = _reservationState.value?.time

        if (date != null && time != null) {
            val localDateTime = LocalDateTime.of(date, time)
            _reservationState.value = _reservationState.value?.copy(dateTime = localDateTime)
        }
    }
}