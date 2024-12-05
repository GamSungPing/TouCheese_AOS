package com.example.domain.model

import com.example.domain.rule.Concept

data class StudioConcepts (
    val data: List<ConceptItem>
)

data class ConceptItem (
    val id: Concept,
    val name: String,
    val mainUrl: String
)