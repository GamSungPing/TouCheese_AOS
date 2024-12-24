package com.example.presentation.main.view.fragment.concept

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.domain.rule.Concept
import com.example.presentation.R
import com.example.presentation.databinding.FragmentHomeConceptMainBinding
import com.example.presentation.screen.concept.vm.HomeConceptViewModel
import dagger.hilt.android.AndroidEntryPoint
import kr.techit.lion.presentation.ext.repeatOnViewStarted

@AndroidEntryPoint
class HomeConceptMainFragment : Fragment(R.layout.fragment_home_concept_main) {
    private val viewModel: HomeConceptViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentHomeConceptMainBinding.bind(view)

        val navController = NavHostFragment.findNavController(
            childFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        )
        repeatOnViewStarted {
            viewModel.event.collect { event ->
                if (navController.currentDestination?.id == R.id.homeConceptFragment && event != Concept.Initial) {
                    val action = HomeConceptFragmentDirections.actionToResultViewFragment(event.id)
                    navController.navigate(action)
                }
            }
        }
        viewModel.backStackRequest.observe(viewLifecycleOwner) {
            navController.popBackStack()
        }
    }
}

