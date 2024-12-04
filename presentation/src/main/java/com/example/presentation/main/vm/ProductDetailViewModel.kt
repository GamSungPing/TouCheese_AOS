package com.example.presentation.main.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Product
import com.example.domain.model.ProductDetail
import com.example.domain.repository.product.ProductRepository
import com.example.presentation.main.vm.model.ReservationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _reservationState = MutableLiveData(ReservationState())
    val reservationState: LiveData<ReservationState> get() = _reservationState

    private val _isOrderEnabled = MutableLiveData<Boolean>()
    val isOrderEnabled: LiveData<Boolean> get() = _isOrderEnabled

    private val _productDetail = MutableLiveData<ProductDetail>()
    val productDetail: LiveData<ProductDetail> get() = _productDetail

    private val _product = MutableLiveData<List<Product>>()
    val product: LiveData<List<Product>> get() = _product

    private val _addGuestCount = MutableLiveData<Int>(0)
    val addGuestCount: LiveData<Int> get() = _addGuestCount

    private val _sumPriceAddGuest = MutableLiveData<Int>()
    val sumPriceAddGuest: LiveData<Int> get() = _sumPriceAddGuest

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

    fun increaseAddGuestCount() {
        viewModelScope.launch {
            val state = _addGuestCount.value ?: return@launch
            _addGuestCount.value = state + 1
        }
    }

    fun decreaseAddGuestCount() {
        viewModelScope.launch {
            val state = _addGuestCount.value ?: return@launch
            if(state > 0) {
                _addGuestCount.value = state - 1
            }
        }

    }

    fun setSumPriceAddGuest() {
        viewModelScope.launch {
            val addPeoplePrice = productDetail.value?.addPeoplePrice ?: 0
            val addGuestCount = addGuestCount.value ?: 0

            _sumPriceAddGuest.value = addPeoplePrice * addGuestCount
        }
    }

    fun getAllProductWithStudioId(studioId: Int) {
        viewModelScope.launch {
            _product.value = productRepository.getAllProductWithStudioId(studioId)
        }
    }

    fun getProductDetailByProductId(productId: Int) {
        viewModelScope.launch {
            _productDetail.value = productRepository.getProductDetailByProductId(productId)
        }
    }

}