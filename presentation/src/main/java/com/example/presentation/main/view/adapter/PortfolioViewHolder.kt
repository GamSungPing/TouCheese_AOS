package com.example.presentation.main.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.presentation.databinding.ItemStudioPortfolioBinding

class PortfolioViewHolder(
    private val binding: ItemStudioPortfolioBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(imageUrl: String) {
        Glide.with(binding.root)
            .load(imageUrl)
            .into(binding.ivPortfolio)
    }
}