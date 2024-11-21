package com.example.presentation.main.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.domain.rule.Concept
import com.example.presentation.R
import com.example.presentation.databinding.FragmentHomeConceptMainBinding
import com.example.presentation.main.vm.HomeConceptViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeConceptMainFragment : Fragment(R.layout.fragment_home_concept_main) {
    private val viewModel: HomeConceptViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeConceptMainBinding.bind(view)

        val navController = NavHostFragment.findNavController(
            childFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        )

        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            val action = HomeConceptFragmentDirections.actionToResultViewFragment(state.id)
            navController.navigate(action)
        }

        viewModel.backStackRequest.observe(viewLifecycleOwner) {
            navController.popBackStack()
        }
    }
}