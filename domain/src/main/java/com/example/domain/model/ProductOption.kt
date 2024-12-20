package com.example.domain.model

import com.example.domain.rule.Output.Companion.optionFormat

data class ProductOption (
    val name: String,
    val price: Int
){
    val optionMessage get() = optionFormat(name, price)
}

val FakeProductOption = listOf(
    ProductOption("보정 사진 추가", 100000),
    ProductOption("원본 전체 받기", 100000),
    ProductOption("액자 프린팅", 100000)
)