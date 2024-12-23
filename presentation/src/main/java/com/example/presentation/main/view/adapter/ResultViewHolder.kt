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
    private val onClickStudio: (String, String) -> Unit,
    private val onClickLike: (Int) -> Unit
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
        val portfolioUrls = studio.portfolioUrls
        val profileURL = studio.profileURL

        binding.tvStudioName.text = name
        binding.tvRating.text = String.format(Locale.US, "%.1f", rating.toDouble())

        val imageView: ImageView = binding.ivStudioMainImage
        imageView.setImage(imageView, profileURL)

        setOnClickStudioListener(id, profileURL)
        setOnClickLikeButtonListener(id.toInt())

        portfolioAdapter.submitList(portfolioUrls)

    }


    private fun setOnClickStudioListener(studioId: String, profileURL: String) {
        binding.root.setOnClickListener {
            onClickStudio(studioId, profileURL)
        }
    }

    private fun setOnClickLikeButtonListener(studioId: Int) {
        binding.btLike.setOnClickListener {
            onClickLike(studioId)
        }
    }
}