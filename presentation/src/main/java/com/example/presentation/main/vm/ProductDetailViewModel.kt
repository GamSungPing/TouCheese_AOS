package com.example.presentation.main.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProductDetailViewModel : ViewModel() {
    val selectedDay = MutableLiveData<android.icu.util.Calendar>()
    val selectedTime = MutableLiveData<String>()

    private val _hasSelectedDay = MutableLiveData(false)
    val hasSelectedDay: LiveData<Boolean> get() = _hasSelectedDay

    private val _hasSelectedTime = MutableLiveData(false)
    val hasSelectedTime: LiveData<Boolean> get() = _hasSelectedTime

    private val _isEnabled = MutableLiveData<Boolean>()
    val isEnabled: LiveData<Boolean> get() = _isEnabled

    fun setButtonEnabled(enabled: Boolean) {
        _isEnabled.value = enabled
    }

    fun setSelectedDay(selected: Boolean) {
        viewModelScope.launch {
            _hasSelectedDay.value = selected
        }
    }

    fun setSelectedTime(selected: Boolean) {
        viewModelScope.launch {
            _hasSelectedTime.value = selected

        }
    }
}