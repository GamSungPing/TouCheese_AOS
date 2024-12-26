package com.example.presentation.main.view.fragment.reserve.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Reservation
import com.example.domain.model.ReservationDetail
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.reservation.ReservationRepository
import com.example.presentation.main.vm.model.MemberStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservationDetailViewModel @Inject constructor(
    private val reservationRepository: ReservationRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _reservations = MutableLiveData<List<Reservation>>()
    val reservations: LiveData<List<Reservation>> get() = _reservations

    private val _reservationDetail = MutableLiveData<ReservationDetail>()
    val reservationDetail: LiveData<ReservationDetail> get() = _reservationDetail

    private val _memberId = MutableStateFlow<MemberStatus>(MemberStatus.Loading)
    val memberId: StateFlow<MemberStatus> get() = _memberId.asStateFlow()

    init {
        authRepository.memberId
            .map {
                if (it > 0L) MemberStatus.Member(it)
                else MemberStatus.NonMember
            }
            .onEach {
                _memberId.value = it
            }.launchIn(viewModelScope)
    }

    fun getReservationsByMemberId(memberId: Long) {
        viewModelScope.launch {
            _reservations.value = reservationRepository.getReservationsByMemberId(memberId)
        }
    }

    fun getReservationDetailByReservationId(reservationId: Int) {
        viewModelScope.launch {
            _reservationDetail.value =
                reservationRepository.getReservationDetailByReservationId(reservationId)
        }
    }

    fun getCompletedReservationByMemberId(memberId: Long) {
        viewModelScope.launch {
            _reservations.value = reservationRepository.getCompletedReservationByMemberId(memberId)
        }
    }

    fun deleteReservationByReservationId(reservationId: Int) {
        viewModelScope.launch {
            reservationRepository.deleteReservationByReservationId(
                reservationId,
                (memberId.value as MemberStatus.Member).memberId
            )
        }
    }
}