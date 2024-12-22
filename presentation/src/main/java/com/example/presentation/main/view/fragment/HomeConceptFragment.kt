package com.example.presentation.main.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.presentation.R
import com.example.presentation.screen.concept.ConceptScreen
import com.example.presentation.screen.concept.vm.HomeConceptViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeConceptFragment : Fragment(R.layout.fragment_home_concept) {
    private val viewModel: HomeConceptViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_home_concept, container, false)
        val composeView = view.findViewById<ComposeView>(R.id.compose_view)
        composeView.setContent {
            ConceptScreen(
                viewModel = viewModel,
                onClickConcept = {
                    viewModel.onChangeScreenState(it)
                }
            )
        }
        return view
    }
}