package com.example.presentation.screen.concept.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.studio.StudioConceptRepository
import com.example.domain.rule.Concept
import com.example.presentation.screen.concept.vm.model.HomeConceptState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeConceptViewModel @Inject constructor(
    private val studioConceptRepository: StudioConceptRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(HomeConceptState.create())
    val uiState: StateFlow<HomeConceptState> get() = _uiState

    private var _event = MutableSharedFlow<Concept>()
    val event: SharedFlow<Concept> get() = _event.asSharedFlow()

    fun onChangeConcept(concept: Concept) {
        viewModelScope.launch {
            _event.emit(concept)
        }
    }

    private val _backStackRequest = MutableLiveData<Unit>()
    val backStackRequest: LiveData<Unit> get() = _backStackRequest

    fun load() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                homeConcept = studioConceptRepository.getStudioConcept()
            )
        }
    }

    fun onRequestBackPress() {
        _backStackRequest.value = Unit
    }
}