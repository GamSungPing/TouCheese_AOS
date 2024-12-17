package com.example.presentation.util.ext

import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.presentation.R
import com.example.presentation.main.vm.model.ReservationStatus

fun TextView.applyStateStyle(backgroundResId: Int, textColorResId: Int) {
    this.setBackgroundResource(backgroundResId)
    this.setTextColor(ContextCompat.getColor(context, textColorResId))
}

fun TextView.setStatusButtonStyle(status: ReservationStatus) {
    this.text = status.status
    when (status) {
        ReservationStatus.CONFIRM -> {
            this.applyStateStyle(
                R.drawable.tc_background_filled_white_outlined_primary07_6,
                R.color.primary07,
            )
        }
        ReservationStatus.CANCEL -> {
            this.applyStateStyle(
                R.drawable.tc_background_filled_white_outlined_red,
                R.color.error,
            )
        }
        ReservationStatus.WAITING -> {
            this.applyStateStyle(
                R.drawable.tc_background_filled_grery03,
                R.color.black
            )
        }
        ReservationStatus.COMPLETE -> {
            this.applyStateStyle(
                R.drawable.tc_background_filled_primary05,
                R.color.black
            )
        }
    }
}