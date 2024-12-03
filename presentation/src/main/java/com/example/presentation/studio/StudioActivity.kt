package com.example.presentation.studio

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.presentation.studio.sideeffect.StudioSideEffect
import com.example.presentation.studio.vm.StudioViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StudioActivity: AppCompatActivity() {
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
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.event.collect { event ->
                    when (event) {
                        StudioSideEffect.CloseScreen -> finish()
                        is StudioSideEffect.NavigateToReservation -> {
                            //val intent = Intent(this@StudioActivity, ReservationActivity::class.java)
                        }
                    }
                }
            }
        }
    }
}