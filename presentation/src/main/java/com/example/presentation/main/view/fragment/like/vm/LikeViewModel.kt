package com.example.presentation.main.view.fragment.like.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Like
import com.example.domain.model.LikeRequest
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.like.LikeRepository
import com.example.presentation.main.vm.model.MemberStatus
import com.example.presentation.util.ext.stateInUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class LikeViewModel @Inject constructor(
    private val likeRepository: LikeRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _likes = MutableLiveData<List<Like>>()
    val likes: LiveData<List<Like>> get() = _likes

    private val _memberId = MutableStateFlow<MemberStatus>(MemberStatus.Loading)
    val memberId: StateFlow<MemberStatus> get() = _memberId.asStateFlow()

    init {
        authRepository.memberId
            .onEach {
                _memberId.value = if (it > 0L) MemberStatus.Member(it)
                else MemberStatus.NonMember
            }.launchIn(viewModelScope)
    }

    fun getLikesByMemberId() {
        viewModelScope.launch {
           _likes.value = likeRepository.getLikes((memberId.value as MemberStatus.Member).memberId)
        }
    }

    fun addLike(studioId: Int, memberId: Long) {
        viewModelScope.launch {
            val likeRequest = LikeRequest(
                createdAt = LocalTime.now().toString(),
                memberId = memberId,
                studioId = studioId
            )
            likeRepository.addLike(likeRequest)
        }
    }

    fun deleteLike(studioId: Int) {
        viewModelScope.launch {
            likeRepository.deleteLike(studioId, (memberId.value as MemberStatus.Member).memberId)
        }
    }
}