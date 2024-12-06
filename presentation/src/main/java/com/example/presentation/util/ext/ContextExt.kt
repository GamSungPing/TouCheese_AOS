package com.example.presentation.util.ext

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.presentation.R

fun Context.drawWithCircleIndicator(view: ImageView, imgUrl: String) {
    Glide.with(this)
        .load(imgUrl)
        .placeholder(
            CircularProgressDrawable(view.context).apply {
                setColorSchemeColors(
                    ContextCompat.getColor(view.context, R.color.black)
                )
                strokeWidth = 2f
                centerRadius = 10f
                start()
            }
        )
        .into(view)
}