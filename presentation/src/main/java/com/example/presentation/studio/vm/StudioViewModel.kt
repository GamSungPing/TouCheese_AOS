package com.example.presentation.studio.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.studio.StudioRepository
import com.example.presentation.studio.vm.model.StudioState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudioViewModel @Inject constructor(
    private val studioRepository: StudioRepository
) : ViewModel() {

    private val _state = MutableStateFlow(StudioState.create())
    val state: StateFlow<StudioState> get() = _state.asStateFlow()

    fun setStudioInfo(studioId: String, studioLogo: String){
        _state.value = _state.value.copy(
            studioId = studioId,
            studioLogo = studioLogo
        )
    }

    fun load(studioId: String, studioLogo: String) {
        viewModelScope.launch {
            val detail = studioRepository.getStudioDetail(studioId.toInt())
            _state.value = _state.value.copy(
                product = detail,
                studioLogo = studioLogo
            )
        }
    }
}