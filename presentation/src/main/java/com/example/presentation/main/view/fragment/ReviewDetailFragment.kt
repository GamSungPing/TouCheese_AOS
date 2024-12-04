package com.example.presentation.main.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.domain.model.detail.Reply
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
    private lateinit var reviewAdapter: ReviewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bind = FragmentReviewDetailBinding.bind(view)
        reviewAdapter = ReviewAdapter()

        settingCarouselRecyclerView(bind)
        viewModel.getReviewDetailByReviewId(1, 1)
        observeReviewDetail(bind)
    }

    private fun settingCarouselRecyclerView(bind: FragmentReviewDetailBinding) {
        val snapHelper = CarouselSnapHelper()
        bind.rvReview.apply {
            adapter = reviewAdapter
            layoutManager = CarouselLayoutManager(FullScreenCarouselStrategy())
        }

        snapHelper.attachToRecyclerView(bind.rvReview)
    }

    private fun observeReviewDetail(bind: FragmentReviewDetailBinding) {
        viewModel.reviewDetail.observe(viewLifecycleOwner) {reviewDetail ->

            updateUserName(reviewDetail.userName, bind)
            updateReviewImagesRecyclerView(reviewDetail.imageStrings, bind)
            updateReviewRating(reviewDetail.rating, bind)
            updateStudioReply(reviewDetail.reply, bind)

            reviewDetail.content?.let {
                updateReviewContent(it, bind)
            }

            reviewDetail.userProfileImageString?.let {
                imageBind(it, bind, bind.ivUserProfileImage)
            }
        }
    }

    private fun updateUserName(userName : String, bind: FragmentReviewDetailBinding) {
        bind.toolbarReview.title = userName
    }

    private fun updateReviewImagesRecyclerView(imageUrls : List<String>, bind: FragmentReviewDetailBinding) {
        reviewAdapter.submitList(imageUrls)
    }

    private fun updateReviewContent(content: String, bind: FragmentReviewDetailBinding) {
        bind.tvReviewText.text = content
    }

    private fun updateReviewRating(rating: Int, bind: FragmentReviewDetailBinding) {
        bind.tvReplyRating.text = String.format("%.1f", rating.toDouble())
    }

    private fun updateStudioReply(reply: Reply, bind: FragmentReviewDetailBinding) {
        bind.tvStudioName.text = reply.studioName
        bind.tvReviewWriteDay.text = formatDateString(reply.dateString)
        bind.tvStudioReplyText.text = reply.content
    }

    private fun imageBind(imageUrl: String, bind: FragmentReviewDetailBinding, imageView: ImageView) {
        Glide.with(bind.root)
            .load(imageUrl)
            .into(imageView)
    }

    fun formatDateString(dateString: String): String {
        return dateString.replace("T", " ")
    }
}