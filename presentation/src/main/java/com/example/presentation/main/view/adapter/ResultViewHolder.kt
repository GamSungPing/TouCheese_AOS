package com.example.presentation.main.view.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.StudioInfoWithConcept
import com.example.presentation.databinding.ItemResultViewBinding

class ResultViewHolder(
    private val binding: ItemResultViewBinding
) : RecyclerView.ViewHolder(binding.root) {
    private val portfolioAdapter = PortfolioAdapter()

    init {
        binding.rvPortfolioList.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPortfolioList.adapter = portfolioAdapter
    }

    fun bind(studio: StudioInfoWithConcept) {
        val id = studio.id
        val name = studio.name
        val rating = studio.rating
        val profilePrice = studio.profilePrice
        val portfolioUrls = studio.portfolioUrls
        val profileURL = studio.profileURL

        binding.tvStudioName.text = name
        binding.tvRating.text = rating
        imageBind(profileURL)

        portfolioAdapter.submitList(portfolioUrls)
    }

    fun imageBind(imageUrl: String) {
        Glide.with(binding.root)
            .load(imageUrl)
            .into(binding.ivStudioMainImage)
    }
}