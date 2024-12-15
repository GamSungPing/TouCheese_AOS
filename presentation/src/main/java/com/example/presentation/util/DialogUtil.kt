package com.example.presentation.util

import android.app.AlertDialog
import android.content.Context

object DialogUtil {
    fun showConfirmDialog(
        context: Context,
        title: String,
        message: String,
        positiveText: String = "확인",
        negativeText: String = "취소",
        onPositiveClick: () -> Unit,
        onNegativeClick: (() -> Unit)? = null
    ) {
        val builder = AlertDialog.Builder(context)
        builder
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveText) { dialog, _ ->
                onPositiveClick.invoke()
                dialog.dismiss()
            }
            .setNegativeButton(negativeText) { dialog, _ ->
                onNegativeClick?.invoke()
                dialog.dismiss()
            }
            .setCancelable(false)
        builder.create().show()
    }
}