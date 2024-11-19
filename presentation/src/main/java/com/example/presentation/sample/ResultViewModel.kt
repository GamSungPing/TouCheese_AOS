package com.example.presentation.sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultViewModel : ViewModel() {
    private val _selectedButton = MutableLiveData<String?>()
    val selectedButton: LiveData<String?> get() = _selectedButton

    fun setSelectedButton(selectedButton: String) {
        if (_selectedButton.value == selectedButton) {
            _selectedButton.value = null
        } else {
            _selectedButton.value = selectedButton
        }
    }
}