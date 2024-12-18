package com.example.presentation.main.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Reservation
import com.example.domain.model.ReservationDetail
import com.example.domain.repository.reservation.ReservationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class ReservationDetailViewModel @Inject constructor(
    private val reservationRepository: ReservationRepository
) : ViewModel() {
    private val _reservations = MutableLiveData<List<Reservation>>()
    val reservations: LiveData<List<Reservation>> get() = _reservations

    private val _reservationDetail = MutableLiveData<ReservationDetail>()
    val reservationDetail: LiveData<ReservationDetail> get() = _reservationDetail

    fun getReservationsByMemberId(memberId: Int) {
        viewModelScope.launch {
            _reservations.value = reservationRepository.getReservationsByMemberId(memberId)
        }
    }

    fun getReservationDetailByReservationId(reservationId: Int) {
        viewModelScope.launch {
            _reservationDetail.value = reservationRepository.getReservationDetailByReservationId(reservationId)
        }
    }

    fun getCompletedReservationByMemberId(memberId: Int) {
        viewModelScope.launch {
            _reservations.value = reservationRepository.getCompletedReservationByMemberId(memberId)
        }
    }

    fun deleteReservationByReservationId(reservationId: Int, memberId: Int) {
        viewModelScope.launch {
            reservationRepository.deleteReservationByReservationId(reservationId, memberId)
        }
    }
}