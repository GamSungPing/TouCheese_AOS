package com.example.presentation.main.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Reservation
import com.example.presentation.databinding.ItemReservationBinding
import com.example.presentation.main.vm.model.ReservationStatus
import com.example.presentation.util.ext.removeSecondsFromTime
import com.example.presentation.util.ext.setImage
import com.example.presentation.util.ext.setStatusButtonStyle

class ReservationViewHolder (
    private val binding: ItemReservationBinding,
    private val onClickReservation: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        setReservationOnClickListener()
    }

    fun bind(reservation: Reservation) {
        with(binding) {
            tvReservationStudio.text = reservation.studioName
            tvReservationState.text = reservation.reservationStatus
            tvReservationDay.text = reservation.reservationDate

            val formattedTime = removeSecondsFromTime(reservation.reservationTime)
            tvReservationTime.text = formattedTime
        }

        val studioImage = binding.ivCard
        studioImage.setImage(studioImage, reservation.studioImage)

        val reservationStatus = ReservationStatus.fromString(reservation.reservationStatus)
        binding.tvReservationState.text = reservationStatus.status
        binding.tvReservationState.setStatusButtonStyle(reservationStatus)

    }

    private fun setReservationOnClickListener() {
        binding.root.setOnClickListener {
            onClickReservation(adapterPosition)
        }
    }
}