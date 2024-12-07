package com.example.presentation.main.view.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.presentation.R
import com.example.presentation.databinding.FragmentReservationDetailBinding

class ReservationDetailFragment : Fragment(R.layout.fragment_reservation_detail) {
    private val args: ReservationDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentReservationDetailBinding.bind(view)
        setToolbar(binding)
        setCancelDialog(binding)
    }

    private fun setToolbar(binding: FragmentReservationDetailBinding) {
        binding.reservationDetailToolbar.setNavigationOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_reservationDetailFragment_to_reservationFragment)
        }
    }

    private fun setCancelDialog(binding: FragmentReservationDetailBinding) {
        binding.btCancelReservation.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder
                .setTitle("예약 취소")
                .setMessage("예약을 취소하면 복구할 수 없습니다")
                .setPositiveButton("확인") { dialog, which ->

                }
                .setNegativeButton("취소") { dialog, which ->

                }


            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

    }
}