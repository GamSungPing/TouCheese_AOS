package com.example.presentation.sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.presentation.R

class ResultViewModel : ViewModel() {
    private val _selectedPrice = MutableLiveData<String?>()
    val selectedButton: LiveData<String?> get() = _selectedPrice

    fun setSelectedButton(selectedButton: String) {
        if (_selectedPrice.value == selectedButton) {
            _selectedPrice.value = null
        } else {
            _selectedPrice.value = selectedButton
        }
    }

    private val _checkboxStates = MutableLiveData<Map<Int, Boolean>>()
    val checkboxStates: LiveData<Map<Int, Boolean>> get() = _checkboxStates

    // 초기화
    init {
        _checkboxStates.value = mapOf(
            R.id.bt_region1 to false,
            R.id.bt_region2 to false,
            R.id.bt_region3 to false,
            R.id.bt_region4 to false,
            R.id.bt_region5 to false,
            R.id.bt_region6 to false,
            R.id.bt_region7 to false,
            R.id.bt_region8 to false,
            R.id.bt_region9 to false
        )
    }


    // 체크박스 상태 업데이트
    fun updateCheckboxState(checkboxId: Int, isChecked: Boolean) {
        _checkboxStates.value = _checkboxStates.value?.toMutableMap()?.apply {
            put(checkboxId, isChecked)
        }
    }

    // 체크된 항목들만 필터링
    fun getCheckedRegions(): List<String> {
        val checkedRegions = mutableListOf<String>()
        _checkboxStates.value?.forEach { (id, isChecked) ->
            if (isChecked) {
                when (id) {
                    R.id.bt_region1 -> checkedRegions.add("강남")
                    R.id.bt_region2 -> checkedRegions.add("서초")
                    R.id.bt_region3 -> checkedRegions.add("송파")
                    R.id.bt_region4 -> checkedRegions.add("강서")
                    R.id.bt_region5 -> checkedRegions.add("마포")
                    R.id.bt_region6 -> checkedRegions.add("영등포")
                    R.id.bt_region7 -> checkedRegions.add("강북")
                    R.id.bt_region8 -> checkedRegions.add("용산")
                    R.id.bt_region9 -> checkedRegions.add("성동")
                }
            }
        }
        return checkedRegions
    }



}