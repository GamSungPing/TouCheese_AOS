package com.example.domain.model

import com.example.domain.rule.Output.Companion.optionFormat

data class ProductOption (
    val name: String,
    val price: Int
){
    val optionMessage get() = optionFormat(name, price)
}