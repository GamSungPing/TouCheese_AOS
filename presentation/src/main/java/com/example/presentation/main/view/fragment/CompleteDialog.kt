package com.example.presentation.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentCompleteDialogBinding

class CompleteDialogFragment(
    val text: String,
) : DialogFragment() {

    private var _binding: FragmentCompleteDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle
        ?
    ): View {
        _binding = FragmentCompleteDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        dialog?.window?.setBackgroundDrawableResource(R.drawable.tc_background_rounded_alert)
        binding.tvComplete.text = text

        binding.yesButton.setOnClickListener {
            dismiss()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}