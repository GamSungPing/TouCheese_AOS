package com.example.presentation.main.view.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.presentation.R
import com.example.presentation.databinding.FragmentReservationDetailBinding
import com.example.presentation.main.vm.ReservationDetailViewModel
import com.example.presentation.main.vm.model.ProductOption
import com.example.presentation.main.vm.model.ReservationStatus
import com.example.presentation.util.ext.toKoreanUnit
import com.example.presentation.util.ext.removeSecondsFromTime
import com.example.presentation.util.ext.setImage
import com.example.presentation.util.ext.setStatusButtonStyle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservationDetailFragment : Fragment(R.layout.fragment_reservation_detail) {
    private val args: ReservationDetailFragmentArgs by navArgs()
    private val viewModel: ReservationDetailViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentReservationDetailBinding.bind(view)
        setToolbar(binding)
        setAlertDialog(binding)
        getReservationDetail()
        observeReservationDetail(binding)
    }

    private fun setToolbar(binding: FragmentReservationDetailBinding) {
        binding.reservationDetailToolbar.setNavigationOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_reservationDetailFragment_to_reservationFragment)
        }
    }

    private fun setAlertDialog(binding: FragmentReservationDetailBinding) {
        binding.btCancelReservation.setOnClickListener {
            val dialog = ConfirmDialog(
                text = "정말 예약을 취소하시겠습니까?",
            ) {
                viewModel.deleteReservationByReservationId(args.reservationId, args.memberId)
                showCompleteDialog()
            }
            dialog.show(childFragmentManager, "ConfirmDialog")
        }
    }

    private fun showCompleteDialog() {
        val dialog = CompleteDialog(text = "예약이 취소되었습니다")
        dialog.show(childFragmentManager, "CompleteDialog")
    }

    private fun getReservationDetail() {
        viewModel.getReservationDetailByReservationId(args.reservationId)
    }

    private fun observeReservationDetail(binding: FragmentReservationDetailBinding) {
        viewModel.reservationDetail.observe(viewLifecycleOwner) {
            with(binding) {
                tvStudioNameContent.text = it.studioName
                tvStudioAddrContent.text = it.studioAddress

                tvUserNameContent.text = it.memberName
                tvUserPhoneContent.text = it.phoneNumber
                tvUserEmailContent.text = it.memberEmail

                tvReservationDayContent.text = it.reservationDate

                val formattedTime = removeSecondsFromTime(it.reservationTime)
                tvReservationTimeContent.text = formattedTime

                tvProductName.text = it.productName
                tvProductTotalPrice.text = it.totalPrice.toKoreanUnit()

                tvOptionAddGuestCnt.text = "${it.addPeopleCnt}명"
                tvOptionAddGuestPrice.text = it.addPeoplePrice.toKoreanUnit()

                val reservationStatus = ReservationStatus.fromString(it.reservationStatus)
                tvReservationStatus.setStatusButtonStyle(reservationStatus)

                ivProductImage.setImage(ivProductImage, it.productImage)
                bindProductOption(it.productOption, binding)

                tvPayInfoProductName.text = it.productName
                tvPayInfoProductPrice.text = it.productPrice.toKoreanUnit()

                tvPayInfoAddPeopleCnt.text = "${it.addPeopleCnt}명"
                tvPayInfoAddPeoplePrice.text = it.addPeoplePrice.toKoreanUnit()
            }
        }
    }

    private fun formatProductOptions(addOption: String): List<ProductOption> {
        val optionsList = addOption.split("@")
        return optionsList.map { option ->
            val splitOption = option.split(":")
            val name = splitOption.getOrNull(0)?.trim() ?: ""
            val price = splitOption.getOrElse(1) { "0" }.trim()
            ProductOption(name, price)
        }.filterNot { it.isEmpty() }
    }

    private fun bindProductOption(addOption: String, binding: FragmentReservationDetailBinding) {
        val options = formatProductOptions(addOption)

        if (options.isNotEmpty()) {
            val firstOption = options[0]
            with(binding) {
                tvOptionProductName.text = firstOption.name
                tvOptionProductPrice.text = "${firstOption.price} 원"

                tvPayInfoAddOptionName.text = firstOption.name
                tvPayInfoAddOptionPrice.text = firstOption.price
                showAddOption(true, binding)
            }
        } else {
            with(binding) {
                tvOptionProductName.text = "옵션 없음"
                tvOptionProductPrice.text = ""

                tvPayInfoAddOptionName.text = "추가옵션 없음"
                tvPayInfoAddOptionPrice.text = "0원"
                showAddOption(false, binding)
            }
        }
    }

    private fun showAddOption(isVisible: Boolean, binding: FragmentReservationDetailBinding) {
        with(binding) {
            layoutOptionAddGuestInfo.isVisible = isVisible
        }
    }

}