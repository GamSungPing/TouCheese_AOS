package com.example.presentation.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.presentation.databinding.BottomSheetFilterRegionBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RegionFilterBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var binding: BottomSheetFilterRegionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetFilterRegionBinding.inflate(inflater, container, false)
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