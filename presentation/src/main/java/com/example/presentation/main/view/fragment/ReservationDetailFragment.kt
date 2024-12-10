package com.example.presentation.main.view.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.presentation.R
import com.example.presentation.databinding.FragmentReservationDetailBinding
import com.example.presentation.main.vm.ReservationDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservationDetailFragment : Fragment(R.layout.fragment_reservation_detail) {
    private val args: ReservationDetailFragmentArgs by navArgs()
    private val viewModel: ReservationDetailViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentReservationDetailBinding.bind(view)
        setToolbar(binding)
        setCancelDialog(binding)
        getReservationDetail()
        observeReservationDetail(binding)
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

    private fun getReservationDetail() {
        viewModel.getReservationDetailByReservationId(args.reservationId)
    }

    private fun observeReservationDetail(binding: FragmentReservationDetailBinding) {
        viewModel.reservationDetail.observe(viewLifecycleOwner) {
            with(binding) {
                tvStudioNameContent.text = it.studioName
                tvStudioAddrContent.text = it.studioAddress
                tvProductContent.text = it.productOption
                tvReservationDateTimeContent.text = it.reservationDate
                tvProductContent.text = it.productName
                tvUserNameContent.text = it.memberName
                tvUserEmailContent.text = it.memberEmail
                tvUserPhoneContent.text = it.phoneNumber
                tvProductAddOptionContent.text = formatProductOptions(it.productOption)
            }
        }
    }

    private fun formatProductOptions(productOption: String): String {
        val optionsList = productOption.split("@")
        return optionsList.joinToString("\n") { option ->
            val splitOption = option.split(":")
            val name = splitOption[0]
            val price = splitOption.getOrElse(1) { "0" }
            "$name : $price 원"
        }
    }
}