package com.example.presentation.sample

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.ItemResultViewBinding

class ResultViewHolder(
    private val binding: ItemResultViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(studio: Studio) {
        val name = studio.name.toString().removeSurrounding("[", "]")
        val images = studio.images

        binding.tvStudioName.text = name


        val portfolioAdapter = PortfolioAdapter()
        portfolioAdapter.submitList(images)

        binding.rvPortfolioList.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPortfolioList.adapter = portfolioAdapter
    }
}