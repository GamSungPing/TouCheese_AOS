package com.example.presentation.product.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ProductDetail
import com.example.domain.repository.product.ProductRepository
import com.example.presentation.product.vm.model.ProductState
import com.example.presentation.product.vm.model.Reservation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ProductState.create())
    val state: StateFlow<ProductState> get() = _state

    private val _isOrderEnabled = MutableLiveData<Boolean>()
    val isOrderEnabled: LiveData<Boolean> get() = _isOrderEnabled

    private val _addGuestCount = MutableLiveData<Int>(0)
    val addGuestCount: LiveData<Int> get() = _addGuestCount

    private val _sumPriceAddGuest = MutableLiveData<Int>()
    val sumPriceAddGuest: LiveData<Int> get() = _sumPriceAddGuest

    fun setButtonEnabled(enabled: Boolean) {
        _isOrderEnabled.value = enabled
    }

    fun increaseAddGuestCount() {
        val state = _addGuestCount.value ?: 0
        _addGuestCount.value = state + 1
    }

    fun decreaseAddGuestCount() {
        val state = _addGuestCount.value ?: 0
        if (state > 0) {
            _addGuestCount.value = state - 1
        }
    }

    fun setSelectedDateTime(selectedDay: LocalDate) {
        _state.value = _state.value.copy(
            reservation = _state.value.reservation.copy(date = selectedDay)
        )
    }

    fun setSelectedTime(selectedDay: LocalTime) {
        _state.value = _state.value.copy(
            reservation = _state.value.reservation.copy(time = selectedDay)
        )
    }

    fun setSumPriceAddGuest() {
//        val addPeoplePrice = _state.value?.addPeoplePrice ?: 0
//        val addGuestCount = addGuestCount.value ?: 0
//
//        _sumPriceAddGuest.value = addPeoplePrice * addGuestCount
    }

    fun getProductDetailByProductId(productId: Int) {
        viewModelScope.launch {
            val product = productRepository.getProductDetailByProductId(productId)
            _state.value = state.value.copy(product = product)
        }
    }
}