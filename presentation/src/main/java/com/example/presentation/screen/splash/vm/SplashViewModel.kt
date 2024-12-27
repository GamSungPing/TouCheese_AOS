package com.example.presentation.screen.splash.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Activation
import com.example.domain.repository.ActivationRepository
import com.example.presentation.util.ext.stateInUi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    activationRepository: ActivationRepository
) : ViewModel() {
    val activation = activationRepository.activation.stateInUi(
        scope = viewModelScope,
        initialValue = Activation.Loading
    )
}