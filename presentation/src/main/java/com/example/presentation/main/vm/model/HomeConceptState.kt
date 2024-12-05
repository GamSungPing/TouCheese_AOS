package com.example.presentation.main.vm.model

import com.example.domain.model.StudioConcepts
import com.example.domain.rule.Concept

data class HomeConceptState(
    val homeConcept: StudioConcepts,
    val concept: Concept
){
    companion object{
        fun create(): HomeConceptState{
            return HomeConceptState(
                homeConcept = StudioConcepts(
                    emptyList()
                ),
                concept = Concept.Initial
            )
        }
    }
}
