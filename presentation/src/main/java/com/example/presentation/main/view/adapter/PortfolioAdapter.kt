package com.example.presentation.main.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.presentation.databinding.ItemStudioPortfolioBinding
import com.example.presentation.util.ext.preloadImage


class PortfolioAdapter : ListAdapter<String, PortfolioViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortfolioViewHolder {
        return PortfolioViewHolder(
            ItemStudioPortfolioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PortfolioViewHolder, position: Int) {
        val imageUrl = getItem(position)
        val context = holder.itemView.context

        setImagePreload(context, position)
        holder.bind(imageUrl)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }

    private fun setImagePreload(context: Context, position: Int) {
        if (position < currentList.size - 1) {
            context.preloadImage(currentList[position + 1])
        }
        if (position > 0) {
            context.preloadImage(currentList[position - 1])
        }
    }
}