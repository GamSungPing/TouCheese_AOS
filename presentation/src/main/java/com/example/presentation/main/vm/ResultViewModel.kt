package com.example.presentation.main.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.StudioInfoWithConcept
import com.example.domain.repository.studio.StudioRepository
import com.example.domain.rule.Pricing
import com.example.domain.rule.Region
import com.example.presentation.main.vm.model.FilterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val studioRepository: StudioRepository
) : ViewModel() {

    private val _filterState = MutableLiveData(FilterState.create())
    val filterState: LiveData<FilterState> get() = _filterState

    private val _result = MutableLiveData<List<StudioInfoWithConcept>>()
    val result: LiveData<List<StudioInfoWithConcept>> get() = _result

    fun onSelectedRegion(region: Region, conceptId: Int) {
        val currentRegion = filterState.value?.regions
        currentRegion?.set(region, currentRegion[region]?.not() ?: false)
        filterState.value?.let {
            getStudioWithConcept(it, conceptId)
        }
    }

    fun onSelectedPrice(price: Pricing, conceptId: Int) {
        getStudioWithConceptOrderByLowerPrice(conceptId, price)
    }

    fun onSelectedOrderByRating(conceptId: Int) {
        var currentState = filterState.value?.orderByRating

        currentState.let {
            currentState = currentState?.not()
        }

        if(filterState.value?.orderByRating == true) {
            getStudioWithConceptOrderByHighRating(conceptId)
        }
    }

    private fun getStudioWithConcept(state: FilterState, conceptId: Int) {
        viewModelScope.launch {
            clear()
            val result = if (state.hasSelectedRegion()) {
                 studioRepository.getStudioWithConceptAndRegion(
                    conceptId, state.getSelectedRegionIds()
                )
            } else{
                studioRepository.getStudioOnlyConcept(conceptId, null)
            }
            _result.value = result
        }
    }

    fun getInitializedStudio(conceptId: Int) {
        viewModelScope.launch {
            clear()
            val result = studioRepository.getStudioOnlyConcept(conceptId, null)
            _result.value = result
        }
    }

    private fun getStudioWithConceptOrderByHighRating(conceptId: Int) {
        viewModelScope.launch {
            clear()
            val result = studioRepository.getStudioWithConceptOrderByHighRating(conceptId, null)
            _result.value = result
        }
    }

    private fun getStudioWithConceptAndRegionsOrderByPrice(state: FilterState, conceptId: Int) {
        viewModelScope.launch {
            clear()
            if(state.price != null) {
                val result = studioRepository.getStudioWithConceptOrderByLowerPrice(conceptId, state.price, null)
                _result.value = result
            }
        }
    }

    private fun getStudioWithConceptOrderByLowerPrice(conceptId: Int, priceCategory: Pricing) {
        viewModelScope.launch {
            clear()
            val result = studioRepository.getStudioWithConceptOrderByLowerPrice(conceptId, priceCategory, null)
            _result.value = result
        }
    }

    private fun clear(){
        _result.value = emptyList()
    }

    fun filterOptionClear() {


    }

}