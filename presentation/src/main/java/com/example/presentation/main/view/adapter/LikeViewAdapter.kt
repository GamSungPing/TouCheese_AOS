package com.example.presentation.main.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.model.Like
import com.example.presentation.databinding.ItemLikeBinding
import com.example.presentation.util.ext.preloadImage

class LikeViewAdapter(
    private val onClickStudio: (String, String) -> Unit
) : ListAdapter<Like, LikeViewHolder>(LikeDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeViewHolder {
        return LikeViewHolder(
            ItemLikeBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClickStudio
        )
    }

    override fun onBindViewHolder(holder: LikeViewHolder, position: Int) {
        val like = getItem(position)
        val context: Context = holder.itemView.context

        setImagePreload(context, position)
        holder.bind(like)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    companion object {
        private val LikeDiffCallback = object : DiffUtil.ItemCallback<Like>() {
            override fun areItemsTheSame(
                oldItem: Like,
                newItem: Like
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Like,
                newItem: Like
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    private fun setImagePreload(context: Context, position: Int) {
        if (position < currentList.size - 1) {
            context.preloadImage(currentList[position + 1].profileURL)
        }
        if (position > 0) {
            context.preloadImage(currentList[position - 1].profileURL)
        }
    }
}