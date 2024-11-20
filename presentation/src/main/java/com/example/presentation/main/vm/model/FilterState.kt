package com.example.presentation.main.vm.model

import com.example.domain.rule.Region

data class FilterState (
    val regions: MutableMap<Region, Boolean>
){
    fun getSelectedRegionIds(): List<Int>{
        return regions.filterValues { it }.keys.map { it.id }
    }

    companion object{
        fun create(): FilterState{
            return FilterState(Region.create())
        }
    }
}