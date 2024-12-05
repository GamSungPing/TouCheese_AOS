package com.example.presentation.main.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.domain.rule.Concept
import com.example.presentation.R
import com.example.presentation.databinding.FragmentHomeConceptBinding
import com.example.presentation.main.vm.HomeConceptViewModel
import com.example.presentation.main.vm.model.HomeConceptState
import dagger.hilt.android.AndroidEntryPoint
import kr.techit.lion.presentation.ext.repeatOnViewStarted

@AndroidEntryPoint
class HomeConceptFragment : Fragment(R.layout.fragment_home_concept) {
    private val viewModel: HomeConceptViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeConceptBinding.bind(view)

        viewModel.load()

        repeatOnViewStarted {
            viewModel.uiState.collect { state ->
                state.homeConcept.data.forEach {
                    when (it.id) {
                        Concept.Initial -> return@forEach
                        Concept.Actor -> {
                            setTitle(binding.tvConceptActor, it.name)
                            draw(binding.btnConceptActor, it.mainUrl)
                        }

                        Concept.Doll -> {
                            setTitle(binding.tvConceptDoll, it.name)
                            draw(binding.btnConceptDoll, it.mainUrl)
                        }

                        Concept.FlashIdol -> {
                            setTitle(binding.tvConceptFlash, it.name)
                            draw(binding.btnConceptFlash, it.mainUrl)
                        }

                        Concept.Liveliness -> {
                            setTitle(binding.tvConceptLiveliness, it.name)
                            draw(binding.btnConceptLiveliness, it.mainUrl)
                        }

                        Concept.Natural -> {
                            setTitle(binding.tvConceptNatural, it.name)
                            draw(binding.btnConceptNatural, it.mainUrl)
                        }

                        Concept.WaterColor -> {
                            setTitle(binding.tvConceptWatercolor, it.name)
                            draw(binding.btnConceptWatercolor, it.mainUrl)
                        }
                    }
                }
            }
        }

        with(binding) {
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


    private fun setTitle(view: TextView, title: String) {
        view.text = title
    }

    private fun draw(view: ImageView, imgUrl: String) {
        Glide.with(this)
            .load(imgUrl)
            .into(view)

    }
}
