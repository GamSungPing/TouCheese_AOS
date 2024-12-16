package com.example.presentation.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentConfirmDialogBinding

class ConfirmDialog(
    text: String,
    id: Int
) : DialogFragment() {

    private var _binding: FragmentConfirmDialogBinding? = null
    private val binding get() = _binding!!
    private var text: String? = null
    private var id: Int? = null

    init {
        this.text = text
        this.id = id
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle
        ?
    ): View {
        _binding = FragmentConfirmDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        dialog?.window?.setBackgroundDrawableResource(R.drawable.tc_background_rounded_alert)
        binding.confirmTextView.text = text

        binding.noButton.setOnClickListener {
            dismiss()
        }

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