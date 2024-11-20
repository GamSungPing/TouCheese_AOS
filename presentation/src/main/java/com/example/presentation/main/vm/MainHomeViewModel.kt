package com.example.presentation.main.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.studio.StudioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainHomeViewModel @Inject constructor(
    private val studioRepository: StudioRepository
) : ViewModel() {
    init {
        viewModelScope.launch {
            studioRepository.getStudioInfo(1)
        }
    }
}