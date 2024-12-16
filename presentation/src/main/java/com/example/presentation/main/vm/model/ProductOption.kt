package com.example.presentation.main.vm.model

data class ProductOption(val name: String, val price: String) {
    fun isEmpty(): Boolean {
        return name.isBlank() && price == "0"
    }
}