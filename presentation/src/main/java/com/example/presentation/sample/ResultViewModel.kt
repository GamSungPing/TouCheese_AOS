package com.example.presentation.sample

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.StudioInfoWithConcept
import com.example.domain.repository.studio.StudioRepository
import com.example.domain.rule.Concept
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
    val selectedButton: LiveData<Pricing?> get() = _selectedPrice

    private val _selectedRegion = MutableLiveData(FilterState.create())
    val selectedRegion: LiveData<FilterState> get() = _selectedRegion

    private val _result = MutableLiveData<List<StudioInfoWithConcept>>()
    val result: LiveData<List<StudioInfoWithConcept>> get() = _result

    fun setSelectedButton(pricing: Pricing) {
        if (_selectedPrice.value == pricing) {
            _selectedPrice.value = null
        } else {
            _selectedPrice.value = pricing
        }
    }

    fun setSelectedRegion(region: Region) {
        val currentRegion = _selectedRegion.value?.regions
        currentRegion?.set(region, currentRegion[region]?.not() ?: false)
        _selectedRegion.value?.let {
            getStudioWithConcept(it)
        }
    }

    private fun getStudioWithConcept(it: FilterState) {
        viewModelScope.launch {
            if (it.takeHasSelectedRegion()) {
                val result = studioRepository.getStudioWithConceptAndRegion(
                    Concept.Celebrity, it.getSelectedRegionIds()
                )
                _result.value = result
            }else{
                studioRepository.getStudioOnlyConcept(Concept.Celebrity, null)
            }
        }
    }

}