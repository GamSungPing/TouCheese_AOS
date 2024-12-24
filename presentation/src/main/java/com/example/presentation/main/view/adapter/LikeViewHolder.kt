package com.example.presentation.main.view.adapter

import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Like
import com.example.presentation.databinding.ItemLikeBinding
import com.example.presentation.util.ext.setImage
import java.util.Locale

class LikeViewHolder(
    private val binding: ItemLikeBinding,
    private val onClickStudio: (String, String) -> Unit,
    private val onClickLike: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private val portfolioAdapter = PortfolioAdapter()

    init {
        binding.rvPortfolioList.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPortfolioList.adapter = portfolioAdapter
    }

    fun bind(like: Like) {
        val id = like.id
        val name = like.name
        val rating = like.rating
        val portfolioUrls = like.portfolioUrls
        val profileURL = like.profileURL
        val reviewCnt = like.reviewCount

        binding.tvLikeStudioName.text = name
        binding.tvLikeRating.text = String.format(Locale.US, "%.1f", rating.toDouble())
        binding.tvLikeReviewCnt.text = "(${reviewCnt})"

        val imageView: ImageView = binding.ivLikeStudioMainImage
        imageView.setImage(imageView, profileURL)

        portfolioAdapter.submitList(portfolioUrls)


        setOnClickStudioListener(id, profileURL)
        setOnClickLikeButtonListener(id.toInt())
    }

    private fun setOnClickStudioListener(studioId: String, profileURL: String) {
        binding.root.setOnClickListener {
            onClickStudio(studioId, profileURL)
        }
    }

    private fun setOnClickLikeButtonListener(studioId: Int) {
        binding.btLikeFilled.setOnClickListener {
            onClickLike(studioId)
        }
    }
}