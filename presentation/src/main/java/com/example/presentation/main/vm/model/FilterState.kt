package com.example.presentation.main.vm.model
import com.example.domain.rule.Pricing
import com.example.domain.rule.Region

data class FilterState (
    val regions: MutableMap<Region, Boolean>,
    val price : Pricing?,
    val orderByRating: Boolean
){
    fun getSelectedRegionIds(): List<Int>{
        return regions.filterValues { it }.keys.map { it.id }
    }

    fun hasSelectedRegion(): Boolean{
        return regions.any { it.value }
    }

    fun hasSelectedPrice(): Boolean{
        return price != null
    }

    fun hasSelectedRegionAndPrice(): Boolean{
        return getSelectedRegionIds().isNotEmpty() && hasSelectedPrice()
    }

    fun hasSelectedAllFilter() : Boolean {
        return getSelectedRegionIds().isNotEmpty() && hasSelectedPrice() && orderByRating
    }

    companion object{
        fun create(): FilterState{
            return FilterState(
                Region.create(),
                null,
                false)
        }
    }
}