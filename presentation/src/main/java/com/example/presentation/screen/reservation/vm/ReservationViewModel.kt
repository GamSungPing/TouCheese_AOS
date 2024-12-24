package com.example.presentation.screen.reservation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.NewReservation
import com.example.domain.repository.reservation.ReservationRepository
import com.example.presentation.navigation.parcelable.ReservationParcelable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor(
    private val reservationRepository: ReservationRepository
) : ViewModel() {

    private var _state = MutableStateFlow(NewReservation.create())
    val state get() = _state.asStateFlow()

    fun setSchedule(data: ReservationParcelable) {
        _state.value = _state.value.copy(
            studioId = data.studio.studioId,
            reservationDate = data.date,
            reservationTime = data.time,
            productId = data.studio.productId,
            productOption = data.selectedOption,
            totalPrice = data.payment,
            addPeopleCnt = data.addPeopleCount ?: 0,
        )
    }

    fun requestReservation(){
        viewModelScope.launch {
            reservationRepository.makeReservation(
                _state.value
            )
        }
    }

    fun onChangeEmail(email: String){
        _state.value = _state.value.copy(email = email)
    }

    fun onChangePhoneNumber(phoneNumber: String){
        _state.value = _state.value.copy(phoneNumber = phoneNumber)
    }

    fun onChangeName(name: String){
        _state.value = _state.value.copy(name = name)
    }
}