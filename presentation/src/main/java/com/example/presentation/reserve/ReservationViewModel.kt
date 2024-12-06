package com.example.presentation.reserve

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.DefaultReserveInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor(
    private val defaultReserveInfoRepository: DefaultReserveInfoRepository
): ViewModel() {

//    val defaultReserveInfo = defaultReserveInfoRepository.info.stateIn(
//        viewModelScope, SharingStarted.WhileSubscribed(5000L),
//        DefaultReserveInfo(email = "", phone = "")
//    )

    fun load() {
        viewModelScope.launch {
            defaultReserveInfoRepository.saveDefaultReserveInfo("email", "phone")
        }
    }
}