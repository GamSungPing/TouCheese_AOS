package com.example.presentation.main.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.domain.rule.Concept
import com.example.presentation.R
import com.example.presentation.databinding.FragmentHomeConceptBinding
import com.example.presentation.main.vm.HomeConceptViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeConceptFragment : Fragment(R.layout.fragment_home_concept) {
    private val viewModel: HomeConceptViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeConceptBinding.bind(view)

        with(binding){
            btnConceptLiveliness.setOnClickListener {
                viewModel.onChangeScreenState(Concept.Liveliness)
            }
            btnConceptFlash.setOnClickListener {
                viewModel.onChangeScreenState(Concept.FlashIdol)
            }
            btnConceptActor.setOnClickListener {
                viewModel.onChangeScreenState(Concept.Actor)
            }
            btnConceptNatural.setOnClickListener {
                viewModel.onChangeScreenState(Concept.Natural)
            }
            btnConceptDoll.setOnClickListener {
                viewModel.onChangeScreenState(Concept.Doll)
            }
            btnConceptWatercolor.setOnClickListener {
                viewModel.onChangeScreenState(Concept.WaterColor)
            }
        }
    }
}
