package com.example.presentation.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.presentation.databinding.FragmentProductDetailViewBinding
import com.example.presentation.main.vm.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailViewFragment : Fragment() {

    private var _binding: FragmentProductDetailViewBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProductDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeReservationState()
        observeOrderButtonEnable()
        showCalendarBottomSheet()
        showChangeCalendarBottomSheet()
        isGroup()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showCalendarBottomSheet() {
        binding.btSelectReservationDay.setOnClickListener {
            val bottomSheet = CalendarFragment()
            bottomSheet.show(childFragmentManager, bottomSheet.tag)
        }
    }

    private fun showChangeCalendarBottomSheet() {
        binding.btChangeReservationDay.setOnClickListener {
            val bottomSheet = CalendarFragment()
            bottomSheet.show(childFragmentManager, bottomSheet.tag)
        }
    }

    private fun isGroup() {
        binding.checkbox1.setOnCheckedChangeListener { _, isGroup ->
            binding.layoutGuest.isVisible = isGroup
            binding.layoutAddGuest.isVisible = isGroup
        }
    }

    private fun observeReservationState() {
        viewModel.reservationState.observe(viewLifecycleOwner) { reservationState ->
        }
    }

    private fun observeOrderButtonEnable() {
        viewModel.isOrderEnabled.observe(viewLifecycleOwner) {
            binding.btOrder.isEnabled = it
        }
    }

    fun updateText(text: String) {
        binding.tvSelectedDate.text = text
    }

    fun updateButtonEnable(isEnabled: Boolean) {
        viewModel.setButtonEnabled(isEnabled)
        toggleVisibility(isEnabled)
    }

    private fun toggleVisibility(boolean: Boolean) {
        binding.btSelectReservationDay.isVisible = !boolean
        binding.tvSelectedDate.isVisible = boolean
        binding.btChangeReservationDay.isVisible = boolean
    }
}