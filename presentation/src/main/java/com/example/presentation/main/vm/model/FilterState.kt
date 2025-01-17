package com.example.presentation.main.vm.model
import com.example.domain.rule.Pricing
import com.example.domain.rule.Region

data class FilterState (
    val regions: MutableMap<Region, Boolean>,
    val pricing : Pricing,
    var hasPriceFilter: Boolean,
    var hasRatingFilter: Boolean
){
    fun getSelectedRegionIds(): List<Int>{
        return regions.filterValues { it }.keys.map { it.id }
    }

    fun hasSelectedRegion(): Boolean{
        return regions.any { it.value }
    }


    companion object{
        fun create(): FilterState{
            return FilterState(
                Region.create(),
                pricing = Pricing.LOW,
                hasRatingFilter = false,
                hasPriceFilter = false
            )
        }
    }
}