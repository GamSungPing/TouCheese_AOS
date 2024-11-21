package com.example.presentation.sample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.model.StudioInfoWithConcept
import com.example.presentation.databinding.ItemResultViewBinding

class ResultViewAdapter : ListAdapter<StudioInfoWithConcept, ResultViewHolder>(StudioDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(
            ItemResultViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val studio = getItem(position)
        holder.bind(studio)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    companion object {
        private val StudioDiffCallback  = object : DiffUtil.ItemCallback<StudioInfoWithConcept>() {
            override fun areItemsTheSame(oldItem: StudioInfoWithConcept, newItem: StudioInfoWithConcept): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: StudioInfoWithConcept, newItem: StudioInfoWithConcept): Boolean {
                return oldItem == newItem
            }
        }
    }
}