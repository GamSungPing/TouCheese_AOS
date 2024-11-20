package com.example.presentation.sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.studio.StudioRepository
import com.example.domain.rule.Concept
import com.example.domain.rule.Region
import com.example.presentation.main.vm.model.FilterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val studioRepository: StudioRepository
) : ViewModel() {
    private val _selectedPrice = MutableLiveData<String?>()
    val selectedButton: LiveData<String?> get() = _selectedPrice

    private val _selectedRegion = MutableLiveData(FilterState.create())
    val selectedRegion: LiveData<FilterState> get() = _selectedRegion

    fun setSelectedButton(selectedButton: String) {
        if (_selectedPrice.value == selectedButton) {
            _selectedPrice.value = null
        } else {
            _selectedPrice.value = selectedButton
        }
    }

    fun setSelectedRegion(region: Region) {
        val currentRegion = _selectedRegion.value?.regions
        currentRegion?.set(region, currentRegion[region]?.not() ?: false)
        viewModelScope.launch {
            _selectedRegion.value?.let {
                val result = studioRepository.getStudioWithConceptAndRegion(
                    Concept.Fancy, it.getSelectedRegionIds()
                )
            }
        }
    }
}