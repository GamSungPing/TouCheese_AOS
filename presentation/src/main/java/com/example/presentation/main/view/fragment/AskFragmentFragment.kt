package com.example.presentation.main.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.presentation.R
import com.example.presentation.databinding.FragmentAskFragmentBinding

class AskFragmentFragment : Fragment(R.layout.fragment_ask_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAskFragmentBinding.bind(view)
    }
}