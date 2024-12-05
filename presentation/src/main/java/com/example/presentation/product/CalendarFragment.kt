package com.example.presentation.product

import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import androidx.fragment.app.activityViewModels
import com.example.presentation.R
import com.example.presentation.databinding.FragmentCalendarBinding
import com.example.presentation.product.vm.ProductDetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class CalendarFragment : BottomSheetDialogFragment(R.layout.fragment_calendar) {
    private val viewModel: ProductDetailViewModel by activityViewModels()
    private val buttonCount: Int = 9

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCalendarBinding.bind(view)
        binding.btDone.setOnClickListener { dismiss() }
        setupTimeButtonCount(requireContext(), binding)
        setupCalendarMinDate(binding)
        updateSelectedDay(binding)
    }

    private fun setupTimeButtonCount(context: Context, binding: FragmentCalendarBinding) {
        val gridLayout = binding.layoutButtonContainer
        for (i in 1..buttonCount) {
            val button = createTimeButton(context, i, binding)
            gridLayout.addView(button)
        }
    }

    private fun createTimeButton(
        context: Context,
        hour: Int,
        binding: FragmentCalendarBinding
    ): MaterialButton {
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        val time = LocalTime.of(hour, 0)
        val buttonText = time.format(timeFormatter)

        val button = MaterialButton(context).apply {
            id = View.generateViewId()
            text = buttonText
            layoutParams = GridLayout.LayoutParams().apply {
                height = GridLayout.LayoutParams.WRAP_CONTENT
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                setMargins(8, 8, 8, 8)
            }
        }

        button.setOnClickListener {
            updateSelectedTime(button, binding)
        }

        return button
    }

    private fun setupCalendarMinDate(binding: FragmentCalendarBinding) {
        val today = Calendar.getInstance().timeInMillis
        binding.reservationCalendar.minDate = today
    }

    private fun updateSelectedTime(button: MaterialButton, binding: FragmentCalendarBinding) {
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        val selectedTime = LocalTime.parse(button.text, timeFormatter)

        viewModel.setSelectedTime(selectedTime)
        binding.btDone.isEnabled = true
    }

    private fun updateSelectedDay(binding: FragmentCalendarBinding) {
        binding.reservationCalendar.setOnDateChangeListener { view, year, month, day ->
            val selectedDay = LocalDate.of(year, month + 1, day)
            viewModel.setSelectedDateTime(selectedDay)
            binding.layoutButtonContainer.visibility = View.VISIBLE
        }
    }
}