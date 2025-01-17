package com.example.presentation.review

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.domain.model.detail.Reply
import com.example.presentation.databinding.ActivityReviewDetailBinding
import com.example.presentation.main.view.adapter.ReviewAdapter
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.FullScreenCarouselStrategy
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewDetailActivity : AppCompatActivity() {

    private val viewModel: ReviewDetailViewModel by viewModels()
    private lateinit var reviewAdapter: ReviewAdapter
    private lateinit var binding: ActivityReviewDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarReview.setNavigationOnClickListener{
            finish()
        }

        val studioId = intent.getIntExtra("studioId", 0)
        val reviewId = intent.getIntExtra("reviewId", 0)

        reviewAdapter = ReviewAdapter()
        setupCarouselRecyclerView()
        viewModel.getReviewDetailByReviewId(studioId, reviewId)
        observeReviewDetail()
    }

    private fun setupCarouselRecyclerView() {
        val snapHelper = CarouselSnapHelper()
        binding.rvReview.apply {
            adapter = reviewAdapter
            layoutManager = CarouselLayoutManager(FullScreenCarouselStrategy())
        }
        snapHelper.attachToRecyclerView(binding.rvReview)
    }

    private fun observeReviewDetail() {
        viewModel.reviewDetail.observe(this) { reviewDetail ->

            updateUserName(reviewDetail.userName)
            updateReviewImagesRecyclerView(reviewDetail.imageStrings)
            updateReviewRating(reviewDetail.rating)
            reviewDetail.reply?.let { updateStudioReply(it) }

            reviewDetail.content?.let {
                updateReviewContent(it)
            }

            reviewDetail.userProfileImageString?.let {
                imageBind(it, binding.ivUserProfileImage)
            }
        }
    }

    private fun updateUserName(userName: String) {
        binding.toolbarReview.title = userName
    }

    private fun updateReviewImagesRecyclerView(imageUrls: List<String>) {
        reviewAdapter.submitList(imageUrls)
    }

    private fun updateReviewContent(content: String) {
        binding.tvReviewText.text = content
    }

    private fun updateReviewRating(rating: Int) {
        binding.tvReplyRating.text = String.format("%.1f", rating.toDouble())
    }

    private fun updateStudioReply(reply: Reply) {
        binding.tvStudioName.text = reply.studioName
        binding.tvReviewWriteDay.text = formatDateString(reply.dateString)
        binding.tvStudioReplyText.text = reply.content
    }

    private fun imageBind(imageUrl: String, imageView: ImageView) {
        Glide.with(this)
            .load(imageUrl)
            .into(imageView)
    }

    private fun formatDateString(dateString: String): String {
        return dateString.replace("T", " ")
    }
}