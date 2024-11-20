package com.example.presentation.main.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.presentation.R
import com.example.presentation.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProfileBinding.bind(view)
    }
}