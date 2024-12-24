package com.example.presentation.screen.profile.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.AuthRepository
import com.example.presentation.screen.profile.vm.model.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileState.create())
    val uiState: StateFlow<ProfileState> = _uiState.asStateFlow()

    val loggedIn = authRepository.loggedIn.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    val memberName = authRepository.memberName.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    fun modifyNickName(nickName: String){
        viewModelScope.launch {
            authRepository.modifyNickName(nickName)
        }
    }

    fun logout(){
        viewModelScope.launch {
            authRepository.logout()
        }
    }

    fun withdraw(){
        viewModelScope.launch {
            authRepository.withdraw()
        }
    }
}