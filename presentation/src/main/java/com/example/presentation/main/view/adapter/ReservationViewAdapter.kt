package com.example.presentation.main.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.presentation.databinding.ItemReservationBinding

class ReservationViewAdapter(
    private val onClickReservation: (Int) -> Unit
) : ListAdapter<String, ReservationViewHolder>(ReservationDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        return ReservationViewHolder(
            ItemReservationBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClickReservation
        )
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        val reservation = getItem(position)
        holder.bind(reservation)
    }

    companion object {
        private val ReservationDiffCallback = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(
                oldItem: String,
                newItem: String
            ): Boolean {
//                return oldItem.id == newItem.id
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: String,
                newItem: String
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}