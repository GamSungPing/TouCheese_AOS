package com.example.presentation.main.view.adapter

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.ItemStudioPortfolioBinding
import com.example.presentation.util.ext.setImage

class PortfolioViewHolder(
    private val binding: ItemStudioPortfolioBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(imageUrl: String) {
        val portfolioImage: ImageView = binding.ivPortfolio
        portfolioImage.setImage(portfolioImage, imageUrl)
    }
}