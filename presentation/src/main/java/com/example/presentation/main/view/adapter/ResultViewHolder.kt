package com.example.presentation.main.view.adapter

import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.StudioInfoWithConcept
import com.example.presentation.databinding.ItemResultViewBinding
import com.example.presentation.util.ext.setImage
import java.util.Locale

class ResultViewHolder(
    private val binding: ItemResultViewBinding,
    private val onClickStudio: (String, String) -> Unit
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
        binding.tvRating.text = String.format(Locale.US, "%.1f", rating.toDouble())

        val imageView: ImageView = binding.ivStudioMainImage
        imageView.setImage(imageView, profileURL)

        portfolioAdapter.submitList(portfolioUrls)

        binding.root.setOnClickListener {
            onClickStudio(id, profileURL)
        }
    }
}