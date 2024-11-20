package com.example.presentation.main.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.presentation.R
import com.example.presentation.databinding.FragmentHomeConceptBinding
import com.example.presentation.main.vm.MainHomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeConceptFragment : Fragment(R.layout.fragment_home_concept) {
    private val viewModel: MainHomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeConceptBinding.bind(view)
        viewModel
    }
}