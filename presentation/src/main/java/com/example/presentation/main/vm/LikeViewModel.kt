package com.example.presentation.main.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Like
import com.example.domain.model.LikeRequest
import com.example.domain.repository.like.LikeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class LikeViewModel @Inject constructor(
    private val likeRepository: LikeRepository
) : ViewModel() {

    private val _likes = MutableLiveData<List<Like>>()
    val likes: LiveData<List<Like>> get() = _likes

    fun getLikesByMemberId(memberId: Int) {
        viewModelScope.launch {
           _likes.value = likeRepository.getLikes(memberId)
        }
    }

    fun addLike(memberId: Int, studioId: Int) {
        viewModelScope.launch {
            val likeRequest = LikeRequest(
                createdAt = LocalTime.now().toString(),
                memberId = memberId,
                studioId = studioId
            )
            likeRepository.addLike(likeRequest)
        }
    }

    fun deleteLike(studioId: Int, memberId: Int) {
        viewModelScope.launch {
            likeRepository.deleteLike(studioId, memberId)
        }
    }
}