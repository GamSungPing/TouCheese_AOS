package com.example.presentation.main.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.presentation.databinding.ItemReviewCarouselBinding

class ReviewViewHolder(
    private val binding: ItemReviewCarouselBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindImage(imageResId: Int) {
        Glide.with(binding.root)
            .load(imageResId)
            .into(binding.ivCarouselReviewImage)
    }
}