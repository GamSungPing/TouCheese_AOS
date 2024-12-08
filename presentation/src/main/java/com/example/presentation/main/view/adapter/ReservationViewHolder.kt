package com.example.presentation.main.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Reservation
import com.example.presentation.databinding.ItemReservationBinding
import com.example.presentation.main.vm.model.ReservationStatus
import com.example.presentation.util.ext.setImage

class ReservationViewHolder (
    private val binding: ItemReservationBinding,
    private val onClickReservation: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        setReservationOnClickListener()
    }

    fun bind(reservation: Reservation) {
        binding.tvReservationStudio.text = reservation.studioName
        binding.tvReservationState.text = reservation.reservationStatus
        binding.tvReservationDateTime.text = reservation.reservationTime

        val studioImage = binding.ivCard
        studioImage.setImage(studioImage, reservation.studioImage)

        val reservationStatus = ReservationStatus.fromString(reservation.reservationStatus)
        binding.tvReservationState.text = reservationStatus.status
    }

    private fun setReservationOnClickListener() {
        binding.root.setOnClickListener {
            onClickReservation(adapterPosition)
        }
    }
}