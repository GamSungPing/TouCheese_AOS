package com.example.presentation.main.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.model.StudioInfoWithConcept
import com.example.presentation.databinding.ItemResultViewBinding
import com.example.presentation.util.ext.preloadImage

class ResultViewAdapter(
    private val onClickStudio: (String, String) -> Unit
) : ListAdapter<StudioInfoWithConcept, ResultViewHolder>(StudioDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(
            ItemResultViewBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClickStudio
        )
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val studio = getItem(position)
        val context: Context = holder.itemView.context

        setImagePreload(context, position)
        holder.bind(studio)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    companion object {
        private val StudioDiffCallback = object : DiffUtil.ItemCallback<StudioInfoWithConcept>() {
            override fun areItemsTheSame(
                oldItem: StudioInfoWithConcept,
                newItem: StudioInfoWithConcept
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: StudioInfoWithConcept,
                newItem: StudioInfoWithConcept
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