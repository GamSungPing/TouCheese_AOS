package com.example.presentation.main.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.presentation.R
import com.example.presentation.databinding.FragmentReviewDetailBinding
import com.example.presentation.main.view.adapter.ReviewAdapter
import com.example.presentation.main.vm.ReviewDetailViewModel
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.FullScreenCarouselStrategy
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewDetailFragment : Fragment(R.layout.fragment_review_detail) {
    private val viewModel: ReviewDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bind = FragmentReviewDetailBinding.bind(view)

        settingToolbar(bind)
        settingCarouselRecyclerView(bind)
        viewModel.getReviewDetailByReviewId(1, 1)
        observeReviewDetail(bind)
    }

    val imageRes = arrayOf(
        R.drawable.image_test2, R.drawable.image_test,
        R.drawable.filter_image1
    )

    private fun settingToolbar(bind: FragmentReviewDetailBinding) {
        bind.toolbarReview.apply {
            title = "김마루"
        }
    }

    private fun settingCarouselRecyclerView(bind: FragmentReviewDetailBinding) {
        val reviewAdapter = ReviewAdapter()
        val snapHelper = CarouselSnapHelper()

        bind.rvReview.apply {
            adapter = reviewAdapter
            layoutManager = CarouselLayoutManager(FullScreenCarouselStrategy())
        }

        snapHelper.attachToRecyclerView(bind.rvReview)
        reviewAdapter.submitList(imageRes.toList())
    }

    private fun observeReviewDetail(bind: FragmentReviewDetailBinding) {
        viewModel.reviewDetail.observe(viewLifecycleOwner) {
            bind.toolbarReview.title = it.userName
            bind.tvReviewText.text = it.content
        }
    }
}