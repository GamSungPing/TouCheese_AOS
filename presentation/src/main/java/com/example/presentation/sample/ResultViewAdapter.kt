package com.example.presentation.sample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.presentation.databinding.ItemResultViewBinding

class ResultViewAdapter : ListAdapter<Studio, ResultViewHolder>(StudioDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(
            ItemResultViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val studio = currentList[position]
        holder.bind(studio)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    companion object {
        private val StudioDiffCallback  = object : DiffUtil.ItemCallback<Studio>() {
            override fun areItemsTheSame(oldItem: Studio, newItem: Studio): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Studio, newItem: Studio): Boolean {
                return oldItem == newItem
            }
        }
    }
}