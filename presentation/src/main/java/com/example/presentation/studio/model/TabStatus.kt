package com.example.presentation.studio.model

sealed class TabStatus(val value: Int, val title: String) {
    data object Price : TabStatus(0, "가격")
    data object Review : TabStatus(1, "리뷰")

    companion object{
        val entries by lazy { listOf(Price, Review) }
    }
}
