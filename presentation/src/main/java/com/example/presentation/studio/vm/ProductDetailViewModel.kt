package com.example.presentation.studio.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.AvailableTime
import com.example.domain.model.ProductOption
import com.example.domain.model.Schedule
import com.example.domain.repository.product.ProductRepository
import com.example.presentation.studio.vm.model.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private var _state = MutableStateFlow(ProductState.create())
    val state: StateFlow<ProductState> get() = _state.asStateFlow()

    fun increaseAddGuestCount() {
        val state = _state.value.addGuestCount + 1
        _state.value = _state.value.copy(addGuestCount = state)
    }

    fun decreaseAddGuestCount() {
        val state = _state.value.addGuestCount
        if (state > 0) {
            _state.value = _state.value.copy(addGuestCount = state - 1)
        }
    }

    fun load(productId: Int) {
        viewModelScope.launch {
            val product = productRepository.getProductDetailByProductId(productId)
            _state.value = _state.value.copy(product = product)
        }
    }

    fun addOption(option: ProductOption) {
        val currentSelectedOption = _state.value.selectedOption.toMutableList()
        currentSelectedOption.add(option)
        _state.value = _state.value.copy(selectedOption = currentSelectedOption)
    }

    fun subOption(option: ProductOption) {
        val currentSelectedOption = _state.value.selectedOption.toMutableList()
        currentSelectedOption.remove(option)
        _state.value = _state.value.copy(selectedOption = currentSelectedOption)
    }

    fun setSchedule(date: LocalDate, time: AvailableTime) {
        _state.value = _state.value.copy(
            schedule = Schedule(date, time)
        )
    }
}