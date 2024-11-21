package com.example.presentation.main.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.rule.Concept

class HomeConceptViewModel : ViewModel() {
    private val _uiState = MutableLiveData<Concept>()
    val uiState: LiveData<Concept> get() = _uiState

    private val _backStackRequest  = MutableLiveData<Unit>()
    val backStackRequest: LiveData<Unit> get() = _backStackRequest

    fun onChangeScreenState(concept: Concept) {
        _uiState.value = concept
    }

    fun onRequestBackPress() {
        _backStackRequest.value = Unit
    }
}