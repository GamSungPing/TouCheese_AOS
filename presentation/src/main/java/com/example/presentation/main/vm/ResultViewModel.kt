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

    fun onSelectedRegion(region: Region) {
        val currentRegion = filterState.value?.regions
        currentRegion?.set(region, currentRegion[region]?.not() ?: false)
    }

    fun getStudioWithConcept(state: FilterState, conceptId: Int) {
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

    fun getStudioWithConceptAndOrderByPrice(conceptId: Int) {
        viewModelScope.launch {
            clear()
            val state = filterState.value ?: return@launch
            val result = studioRepository.getStudioWithConceptOrderByLowerPrice(conceptId, state.pricing)
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

    fun getStudioWithConceptOrderByHighRating(conceptId: Int) {
        viewModelScope.launch {
            clear()
            val result = studioRepository.getStudioWithConceptOrderByHighRating(conceptId, null)
            _result.value = result
        }
    }

    fun getStudioWithConceptAndRegion(conceptId: Int) {
        viewModelScope.launch {
            clear()
            val state = filterState.value ?: return@launch
            val result = studioRepository.getStudioWithConceptAndRegion(conceptId, state.getSelectedRegionIds())
            _result.value = result
        }
    }

    fun clear(){
        _result.value = emptyList()
    }

    fun updatePrice(pricing: Pricing) {
        _filterState.value = _filterState.value?.copy(
            pricing = pricing
        )
    }

    fun updateFilterState(newState: FilterState) {
        _filterState.value = newState
    }


    // 지역 필터 상태 변경 메서드
    fun updateRegions(region: Region, isChecked: Boolean) {
        val updatedRegions = _filterState.value?.regions?.toMutableMap() ?: mutableMapOf()
        updatedRegions[region] = isChecked

        _filterState.value = _filterState.value?.copy(
            regions = updatedRegions
        )
    }

    // 초기화
    fun clearAllFilters() {
        _filterState.value = FilterState.create()
    }

}