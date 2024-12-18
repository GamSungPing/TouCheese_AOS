package com.example.presentation.main.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.model.Reservation
import com.example.presentation.databinding.ItemReservationBinding

class ReservationViewAdapter(
    private val onClickReservation: (Int) -> Unit
) : ListAdapter<Reservation, ReservationViewHolder>(ReservationDiffCallback) {
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
        private val ReservationDiffCallback = object : DiffUtil.ItemCallback<Reservation>() {
            override fun areItemsTheSame(
                oldItem: Reservation,
                newItem: Reservation
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Reservation,
                newItem: Reservation
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}