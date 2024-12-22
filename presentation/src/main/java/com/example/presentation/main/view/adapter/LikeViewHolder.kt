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
    private val onClickStudio: (String, String) -> Unit
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
        val profilePrice = like.profilePrice
        val portfolioUrls = like.portfolioUrls
        val profileURL = like.profileURL

        binding.tvStudioName.text = name
        binding.tvRating.text = String.format(Locale.US, "%.1f", rating.toDouble())

        val imageView: ImageView = binding.ivStudioMainImage
        imageView.setImage(imageView, profileURL)

        portfolioAdapter.submitList(portfolioUrls)

        binding.root.setOnClickListener {
            onClickStudio(id, profileURL)
        }
    }
}