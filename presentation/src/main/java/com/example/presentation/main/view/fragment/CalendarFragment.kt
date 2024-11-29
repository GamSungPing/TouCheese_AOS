package com.example.presentation.main.view.fragment

import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.core.view.isVisible
import com.example.presentation.databinding.FragmentCalendarBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import java.time.LocalDate

class CalendarFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!
    private var selectedDay: Calendar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        makeReservationTimeButton(requireContext())
        setupCalendarMinDate()
        setupDoneButton()
        setupCalendar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun makeReservationTimeButton(context: Context) {
        val buttonCount = 9

        val gridLayout = binding.layoutButtonContainer
        for (i in 1..buttonCount) {
            val button =  MaterialButton(context).apply {
                id = View.generateViewId()
                text = "0$i:00"
                layoutParams = GridLayout.LayoutParams().apply {
                    height = GridLayout.LayoutParams.WRAP_CONTENT
                    columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                    rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                    setMargins(8, 8, 8, 8)  // 버튼 간 간격
                }
            }
            gridLayout.addView(button)
        }
    }

    private fun setupCalendarMinDate() {
        val today = Calendar.getInstance().timeInMillis
        binding.reservationCalendar.minDate = today
    }

    private fun setupCalendar() {
        binding.reservationCalendar.setOnDateChangeListener { view, year, month, day ->
            selectedDay = Calendar.getInstance().apply {
                set(year, month + 1, day)
            }
            hasSelectedDay()
        }
    }

    private fun hasSelectedDay() {
        binding.layoutButtonContainer.visibility = View.VISIBLE
        binding.btDone.isEnabled = true
    }

    private fun setupDoneButton() {
        binding.btDone.setOnClickListener {
            dismiss()
        }
    }
}