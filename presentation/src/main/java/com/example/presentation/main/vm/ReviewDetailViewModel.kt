package com.example.presentation.main.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ReviewDetail
import com.example.domain.repository.review.ReviewDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewDetailViewModel @Inject constructor(
    private val reviewDetailRepository: ReviewDetailRepository
) : ViewModel() {

    private val _reviewDetail = MutableLiveData<ReviewDetail>()
    val reviewDetail : LiveData<ReviewDetail> get() = _reviewDetail

    fun getReviewDetailByReviewId(studioId: Int, reviewId: Int) {
        viewModelScope.launch {
            _reviewDetail.value = reviewDetailRepository.getReviewDetailByReviewId(studioId, reviewId)
            Log.d("test1234", "data : ${reviewDetail.value}")
        }
    }
}