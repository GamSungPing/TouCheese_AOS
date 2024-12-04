package com.example.presentation.main.view.fragment

import android.content.Context
import android.content.DialogInterface
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.presentation.databinding.FragmentCalendarBinding
import com.example.presentation.main.vm.ProductDetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class CalendarFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: ProductDetailViewModel by viewModels({requireParentFragment()})
    private val buttonCount: Int = 9

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
        setupTimeButtonCount(requireContext())
        setupCalendarMinDate()
        setupDoneButton()
        updateSelectedDay()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        parentUIUpdate()
    }

    private fun setupTimeButtonCount(context: Context) {
        val gridLayout = binding.layoutButtonContainer
        for (i in 1..buttonCount) {
            val button = createTimeButton(context, i)
            gridLayout.addView(button)
        }
    }

    private fun createTimeButton(context: Context, hour: Int): MaterialButton {
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
            updateSelectedTime(button)
        }

        return button
    }

    private fun setupCalendarMinDate() {
        val today = Calendar.getInstance().timeInMillis
        binding.reservationCalendar.minDate = today
    }


    private fun updateSelectedTime(button: MaterialButton) {
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        val selectedTime = LocalTime.parse(button.text, timeFormatter)

        sharedViewModel.setSelectedTime(selectedTime)
        updateEnable()
    }

    private fun updateSelectedDay() {
        binding.reservationCalendar.setOnDateChangeListener { view, year, month, day ->
            val selectedDay = LocalDate.of(year, month + 1, day)
            sharedViewModel.setSelectedDay(selectedDay)

            showTimeButton()
        }
    }

    private fun showTimeButton() {
        binding.layoutButtonContainer.visibility = View.VISIBLE
    }

    private fun updateEnable() {
        binding.btDone.isEnabled = true
    }

    private fun setupDoneButton() {
        binding.btDone.setOnClickListener {
            sharedViewModel.setSelectedDateTime()
            sharedViewModel.setHasSelectDateTime(binding.btDone.isEnabled)
            dismiss()
        }
    }

    private fun getDateTimeToString(): String {
        val dateTime = sharedViewModel.reservationState.value?.dateTime
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

        return if (dateTime != null) {
            dateTime.format(formatter)
        } else {
            "선택한 날짜가 없습니다"
        }
    }

    private fun parentUIUpdate() {
        val parentFragment = parentFragment as? ProductDetailViewFragment
        parentFragment?.updateButtonEnable(true)
        parentFragment?.updateText(getDateTimeToString())
    }
}