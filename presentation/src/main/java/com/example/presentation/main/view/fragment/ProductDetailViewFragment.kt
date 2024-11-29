package com.example.presentation.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.presentation.databinding.FragmentProductDetailViewBinding
import com.example.presentation.main.vm.ProductDetailViewModel

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
        showCalendarBottomSheet()
        isGroup()
        observeOrderButton()
        return binding.root
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

    private fun isGroup() {
        binding.checkbox1.setOnCheckedChangeListener { _, isGroup ->
            binding.layoutGuest.isVisible = isGroup
            binding.layoutAddGuest.isVisible = isGroup
        }
    }

    private fun observeOrderButton() {
        viewModel.isEnabled.observe(viewLifecycleOwner, Observer { isEnabled ->
            binding.btOrder.isEnabled = isEnabled
        })
    }

    fun onDateSelected(isEnabled: Boolean) {
        viewModel.setButtonEnabled(isEnabled)
    }
}