package com.example.domain.model

data class StudioInfoWithConcept (
    val id: String,
    val name: String,
    val profilePrice: String,
    val rating: String,
    val portfolioUrls: List<String>,
    val profileURL: String,
    val isSelected: Boolean = false,
)