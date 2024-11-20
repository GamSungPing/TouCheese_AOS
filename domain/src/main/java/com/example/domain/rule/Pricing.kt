package com.example.domain.rule

enum class Pricing {
    LOW, MEDIUM, HIGH;

    override fun toString(): String = this.name
}