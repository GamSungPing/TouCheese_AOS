package com.example.presentation.studio

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.presentation.main.view.ReviewDetailActivity
import com.example.presentation.product.ProductDetailActivity
import com.example.presentation.studio.sideeffect.StudioSideEffect
import com.example.presentation.studio.vm.StudioViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StudioActivity : AppCompatActivity() {
    private val viewModel: StudioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val studioId = intent.getStringExtra("studioId") ?: ""
        val profileURL = intent.getStringExtra("profileURL") ?: ""

        setContent {
            StudioScreen(viewModel)
        }

        viewModel.load(studioId, profileURL)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect { event ->
                    when (event) {
                        StudioSideEffect.CloseScreen -> finish()
                        is StudioSideEffect.NavigateToProductDetail -> {
                            val intent = Intent(
                                this@StudioActivity,
                                ProductDetailActivity::class.java
                            ).apply {
                                this.putExtra("description", event.description)
                                this.putExtra("productId", event.productId)
                                this.putExtra("path", event.path)
                            }
                            startActivity(intent)
                        }
                        is StudioSideEffect.NavigateToReviewDetail -> {
                            val intent = Intent(
                                this@StudioActivity, ReviewDetailActivity::class.java
                            ).apply {
                                this.putExtra("studioId", studioId.toInt())
                                this.putExtra("reviewId", event.reviewId)
                            }
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }
}