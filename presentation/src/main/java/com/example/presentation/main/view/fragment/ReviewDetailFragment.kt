package com.example.presentation.main.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentReviewDetailBinding
import com.example.presentation.main.view.adapter.ReviewAdapter
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.FullScreenCarouselStrategy

class ReviewDetailFragment : Fragment(R.layout.fragment_review_detail) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bind = FragmentReviewDetailBinding.bind(view)

        settingToolbar(bind)
        settingCarouselRecyclerView(bind)
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
}