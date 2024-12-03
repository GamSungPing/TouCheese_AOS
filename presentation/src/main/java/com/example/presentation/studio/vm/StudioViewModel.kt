package com.example.presentation.studio.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.studio.StudioRepository
import com.example.presentation.studio.model.StudioState
import com.example.presentation.studio.model.TabStatus
import com.example.presentation.studio.sideeffect.StudioSideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudioViewModel @Inject constructor(
    private val studioRepository: StudioRepository
) : ViewModel() {

    private val _state = MutableStateFlow(StudioState.create())
    val state: StateFlow<StudioState> get() = _state

    private val _event = MutableSharedFlow<StudioSideEffect>()
    val event: MutableSharedFlow<StudioSideEffect> get() = _event

    fun load(studioId: String, studioLogo: String) {
        viewModelScope.launch {
            val detail = studioRepository.getStudioDetail(studioId.toInt())
            _state.value = _state.value.copy(
                product = detail,
                studioLogo = studioLogo
            )
        }
    }

    fun onClickBackButton(){
        viewModelScope.launch {
            _event.emit(StudioSideEffect.CloseScreen)
        }
    }
}