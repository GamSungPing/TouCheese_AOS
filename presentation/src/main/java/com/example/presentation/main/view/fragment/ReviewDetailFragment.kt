package com.example.presentation.main.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.presentation.R
import com.example.presentation.databinding.FragmentReviewBinding

class ReviewFragment : Fragment(R.layout.fragment_review) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bind = FragmentReviewBinding.bind(view)

        settingPhotoTest(bind)
    }


    fun settingPhotoTest(bind: FragmentReviewBinding) {
        val photoView = bind.photoView
        Glide.with(this)
            .load(R.drawable.image_test)
            .into(photoView)

    }
}