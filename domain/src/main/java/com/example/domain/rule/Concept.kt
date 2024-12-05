package com.example.domain.rule

sealed class Concept(val id: Int) {
    object Initial: Concept(0)
    data object FlashIdol : Concept(1)
    data object Liveliness : Concept(2)
    data object Actor : Concept(3)
    data object Natural : Concept(4)
    data object WaterColor : Concept(5)
    data object Doll : Concept(6)

    companion object{
        fun from(id: Int): Concept {
            return when (id) {
                1 -> FlashIdol
                2 -> Liveliness
                3 -> Actor
                4 -> Natural
                5 -> WaterColor
                6 -> Doll
                else -> throw IllegalArgumentException()
            }
        }
    }
}