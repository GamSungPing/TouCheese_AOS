package com.example.domain.rule

enum class Grouping {
    GROUP, NOT_GROUP;

    companion object {
        fun from(isGroup: Boolean): Grouping {
            return if (isGroup) GROUP else NOT_GROUP
        }
    }
}