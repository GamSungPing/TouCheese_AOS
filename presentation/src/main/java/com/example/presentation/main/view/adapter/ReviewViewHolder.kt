package com.example.presentation.main.view.adapter

import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.presentation.R
import com.example.presentation.databinding.ItemReviewCarouselBinding
import io.getstream.photoview.dialog.PhotoViewDialog

class ReviewViewHolder(
    private val binding: ItemReviewCarouselBinding
) : RecyclerView.ViewHolder(binding.root) {

    private var photoDialog: PhotoViewDialog<Int>? = null
    private val closeDialogButton = ImageButton(binding.root.context)

    val imageRes = arrayOf(
        R.drawable.image_test2, R.drawable.image_test,
        R.drawable.filter_image1
    )

    private fun setupCloseDialogButton() {
        isInitCloseButton()

        closeDialogButton.apply {
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.gravity = Gravity.CENTER
            closeDialogButton.layoutParams = layoutParams

            setImageResource(R.drawable.icon_close_24px)
            setOnClickListener {
                closeDialog()
            }
        }
    }

    private fun isInitCloseButton() {
        closeDialogButton.apply {
            (parent as? ViewGroup)?.removeView(this)
        }
    }

    private fun buildPhotoViewDialog() {
        photoDialog = PhotoViewDialog.Builder(
            binding.root.context,
            imageRes.toList()
        ) { imageView, imageResId ->
            Glide.with(binding.root.context)
                .load(imageResId)
                .into(imageView)
        }.apply {
            setupCloseDialogButton()
            withOverlayView(closeDialogButton)
        }.build()
    }

    private fun showDialog() {
        if (photoDialog == null) {
            buildPhotoViewDialog()
        }
        photoDialog?.show()
    }

    private fun closeDialog() {
        photoDialog?.dismiss()
        photoDialog = null
    }

    fun setupPhotoViewDialog() {
        binding.ivCarouselReviewImage.setOnClickListener {
            showDialog()
        }
    }

    fun bindImage(imageResId: Int) {
        Glide.with(binding.root)
            .load(imageResId)
            .into(binding.ivCarouselReviewImage)
    }
}