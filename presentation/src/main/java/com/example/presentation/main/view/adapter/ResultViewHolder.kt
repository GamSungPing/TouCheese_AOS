package com.example.presentation.main.view.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.StudioInfoWithConcept
import com.example.presentation.databinding.ItemResultViewBinding

class ResultViewHolder(
    private val binding: ItemResultViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(studio: StudioInfoWithConcept) {
        val id = studio.id
        val name = studio.name
        val rating = studio.rating
        val profilePrice = studio.profilePrice

        binding.tvStudioName.text = name
        binding.tvRating.text = rating


        val portfolioAdapter = PortfolioAdapter()
//        portfolioAdapter.submitList(images)

        binding.rvPortfolioList.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPortfolioList.adapter = portfolioAdapter
    }
}