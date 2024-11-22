package com.example.presentation.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.presentation.databinding.BottomSheetFilterPriceBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PriceFilterBottomSheetFragment : BottomSheetDialogFragment() {
    lateinit var binding: BottomSheetFilterPriceBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetFilterPriceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showBottomSheet()
    }

    private fun showBottomSheet() {
        with(binding) {
            btDone.setOnClickListener {

            }
        }
    }
}