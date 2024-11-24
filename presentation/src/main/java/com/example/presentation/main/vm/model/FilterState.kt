package com.example.presentation.main.vm.model
import com.example.domain.rule.Pricing
import com.example.domain.rule.Region

data class FilterState (
    val regions: MutableMap<Region, Boolean>,
    val pricing : Pricing,
){
    fun getSelectedRegionIds(): List<Int>{
        return regions.filterValues { it }.keys.map { it.id }
    }

    fun hasSelectedRegion(): Boolean{
        return regions.any { it.value }
    }

    fun hasSelectedPrice(): Boolean{
        return pricing != Pricing.LOW
    }

    fun clearRegions() {
        return regions.replaceAll { _, _ -> false }
    }

    companion object{
        fun create(): FilterState{
            return FilterState(
                Region.create(),
                pricing = Pricing.LOW
            )
        }
    }
}