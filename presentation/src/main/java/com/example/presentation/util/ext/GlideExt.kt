package com.example.presentation.util.ext

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.presentation.R

fun ImageView.setImage(imageView: ImageView, url: String?) {
    Glide.with(this).load(url)
        .placeholder(R.drawable.loading_gif)
        .into(imageView)
}

fun Context.preloadImage(url: String?) {
    if (url.isNullOrEmpty()) return
    Glide.with(this)
        .load(url)
        .preload(150, 150)
}