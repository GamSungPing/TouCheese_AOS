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

    private var photoDialog: PhotoViewDialog<String>? = null
    private val closeDialogButton = ImageButton(binding.root.context)

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

    fun buildPhotoViewDialog(imageUrl: String) {
        setupCloseDialogButton()

        photoDialog = PhotoViewDialog.Builder(
            binding.root.context,
            listOf(imageUrl)
        ) { imageView, url ->
            Glide.with(binding.root.context)
                .load(url)
                .into(imageView)
        }.apply {
            withOverlayView(closeDialogButton)
        }.build()
    }

    private fun closeDialog() {
        photoDialog?.dismiss()
        photoDialog = null
    }

    private fun showDialog(imageUrl: String) {
        if (photoDialog == null) {
            buildPhotoViewDialog(imageUrl)
        }
        photoDialog?.show()
    }

    fun reviewImageBindAndOnClickListener(imageUrl: String) {
        Glide.with(binding.root)
            .load(imageUrl)
            .into(binding.ivCarouselReviewImage)

        binding.ivCarouselReviewImage.setOnClickListener {
            showDialog(imageUrl)
        }
    }
}