package com.example.presentation.main.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.ItemReservationBinding

class ReservationViewHolder (
    private val binding: ItemReservationBinding,
    private val listener: ReservationViewAdapter.OnReservationClickListener
) : RecyclerView.ViewHolder(binding.root) {

    init {
        setReservationOnClickListener()
    }

    fun bind(item: String) {
        binding.tvReservationStudio.text = item
    }

    private fun setReservationOnClickListener() {
        binding.layoutReservationItem.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }
}