package com.example.presentation.main.vm

import android.util.Log
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

    private var _filterState = MutableLiveData(FilterState.create())
    val filterState: LiveData<FilterState> get() = _filterState

    private val _result = MutableLiveData<List<StudioInfoWithConcept>>()
    val result: LiveData<List<StudioInfoWithConcept>> get() = _result

    private val _empty = MutableLiveData<Boolean>()
    val empty: LiveData<Boolean> get() = _empty

    fun getInitializedStudio(conceptId: Int) {
        viewModelScope.launch {
            clear()
            val result = studioRepository.getStudioOnlyConcept(conceptId, null)
            _result.value = result
        }
    }

    private fun getStudioWithConceptAndOrderByPrice(conceptId: Int) {
        viewModelScope.launch {
            clear()
            val state = filterState.value ?: return@launch
            val result = studioRepository.getStudioWithConceptOrderByLowerPrice(conceptId, state.pricing)
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

    private fun getStudioWithConceptAndRegion(conceptId: Int) {
        viewModelScope.launch {
            clear()
            val state = filterState.value ?: return@launch
            val result = studioRepository.getStudioWithConceptAndRegion(conceptId, state.getSelectedRegionIds())
            _result.value = result
        }
    }

    private fun getStudioWithConceptAndRegionOrderByHighRating(conceptId: Int) {
        viewModelScope.launch {
            clear()
            val state = filterState.value ?: return@launch
            val result = studioRepository.getStudioWithConceptAndRegionOrderByHighRating(conceptId, state.getSelectedRegionIds())
            _result.value = result
        }
    }

    private fun getStudioWithConceptAndRegionsOrderByPrice(conceptId: Int) {
        viewModelScope.launch {
            clear()
            val state = filterState.value ?: return@launch
            val result = studioRepository.getStudioWithConceptAndRegionsOrderByPrice(conceptId, state.getSelectedRegionIds(), state.pricing)
            _result.value = result
        }
    }

    private fun getStudioWithConceptOrderByHighRatingAndLowerPrice(conceptId: Int) {
        viewModelScope.launch {
            clear()
            val state = filterState.value ?: return@launch
            val result = studioRepository.getStudioWithConceptOrderByHighRatingAndLowerPrice(conceptId, state.pricing)
            _result.value = result
        }
    }

    private fun getStudioWithConceptAndAllFilter(conceptId: Int) {
        viewModelScope.launch {
            clear()
            val state = filterState.value ?: return@launch
            val result = studioRepository.getStudioWithConceptAndRegionOrderByHighRatingAndLowerPrice(conceptId, state.pricing, state.getSelectedRegionIds())
            _result.value = result
        }
    }

    fun updatePrice(pricing: Pricing) {
        _filterState.value = _filterState.value?.copy(
            pricing = pricing,
            hasPriceFilter = true
        )
    }

    fun updateRegions(region: Region, isChecked: Boolean) {
        val updatedRegions = _filterState.value?.regions?.toMutableMap() ?: mutableMapOf()
        updatedRegions[region] = isChecked

        _filterState.value = _filterState.value?.copy(
            regions = updatedRegions
        )
    }

    fun updateRating(isChecked: Boolean) {
        _filterState.value = _filterState.value?.copy(
            hasRatingFilter = isChecked
        )
    }

    fun updateEmpty(isEmpty: Boolean) {
        _empty.value = isEmpty
    }

    fun clear(){
        _result.value = emptyList()
    }

    fun clearRegionFilters() {
        _filterState.value = _filterState.value?.copy(
            regions = Region.create()
        )
    }

    fun clearAllFilters() {
        _filterState.value = FilterState.create()
    }

    fun checkFilterOption(conceptId: Int) {
        viewModelScope.launch {
            val state = filterState.value ?: return@launch
            if (state.hasPriceFilter && state.hasRatingFilter && state.hasSelectedRegion()) {
                getStudioWithConceptAndAllFilter(conceptId)
            }
            else if (state.hasPriceFilter && state.hasRatingFilter) {
                getStudioWithConceptOrderByHighRatingAndLowerPrice(conceptId)
            }
            else if (state.hasPriceFilter && state.hasSelectedRegion()) {
                getStudioWithConceptAndRegionsOrderByPrice(conceptId)
            }
            else if (state.hasSelectedRegion() && state.hasRatingFilter) {
                getStudioWithConceptAndRegionOrderByHighRating(conceptId)
            }
            else if (state.hasSelectedRegion()) {
                getStudioWithConceptAndRegion(conceptId)
            }
            else if (state.hasPriceFilter) {
                getStudioWithConceptAndOrderByPrice(conceptId)
            }
            else if (state.hasRatingFilter) {
                getStudioWithConceptOrderByHighRating(conceptId)
            }
            else {
                getInitializedStudio(conceptId)
            }
        }
    }
}