package com.example.domain.rule

sealed class Concept(val id: Int) {
    data object Flash : Concept(1)
    data object Liveliness : Concept(2)
    data object Celebrity : Concept(3)
    data object Natural : Concept(4)
    data object WaterColor : Concept(5)
    data object Fancy : Concept(6)
}