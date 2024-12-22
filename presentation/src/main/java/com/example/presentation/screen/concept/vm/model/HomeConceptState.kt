package com.example.presentation.screen.concept.vm.model

import com.example.domain.model.StudioConcepts
import com.example.domain.rule.Concept

data class HomeConceptState(
    val homeConcept: StudioConcepts
){
    companion object{
        fun create(): HomeConceptState {
            return HomeConceptState(
                homeConcept = StudioConcepts(
                    emptyList()
                )
            )
        }
    }
}
