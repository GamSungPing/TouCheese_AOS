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

    private val _selectedPrice = MutableLiveData<Pricing?>()
    val selectedPrice: LiveData<Pricing?> get() = _selectedPrice

    private val _selectedRegion = MutableLiveData(FilterState.create())
    val selectedRegion: LiveData<FilterState> get() = _selectedRegion

    private val _result = MutableLiveData<List<StudioInfoWithConcept>>()
    val result: LiveData<List<StudioInfoWithConcept>> get() = _result

    fun setSelectedButtonToggle(pricing: Pricing) {
        if (_selectedPrice.value == pricing) {
            _selectedPrice.value = null
        } else {
            _selectedPrice.value = pricing
        }
    }

    fun onSelectedRegion(region: Region, conceptId: Int) {
        val currentRegion = _selectedRegion.value?.regions
        currentRegion?.set(region, currentRegion[region]?.not() ?: false)
        _selectedRegion.value?.let {
            getStudioWithConcept(it, conceptId)
        }
    }

    fun onSelectedPrice() {


    }

    private fun getStudioWithConcept(state: FilterState, conceptId: Int) {
        viewModelScope.launch {
            val result = if (state.takeHasSelectedRegion()) {
                 studioRepository.getStudioWithConceptAndRegion(
                    conceptId, state.getSelectedRegionIds()
                )
            }else{
                studioRepository.getStudioOnlyConcept(conceptId, null)
            }
            _result.value = result
        }
    }

    fun getInitializedStudio(conceptId: Int) {
        viewModelScope.launch {
            val result = studioRepository.getStudioOnlyConcept(conceptId, null)
            _result.value = result
        }
    }

    fun getStudioWithConceptOrderByHighRating(conceptId: Int) {
        viewModelScope.launch {
            val result = studioRepository.getStudioWithConceptOrderByHighRating(conceptId, null)
            _result.value = result
        }
    }

    fun getStudioWithConceptOrderByLowerPrice(conceptId: Int, priceCategory: Pricing) {
        viewModelScope.launch {
            val result = studioRepository.getStudioWithConceptOrderByLowerPrice(conceptId, priceCategory, null)
            _result.value = result
        }
    }

}