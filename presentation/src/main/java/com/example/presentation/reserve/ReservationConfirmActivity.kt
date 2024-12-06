package com.example.presentation.reserve

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.presentation.R
import com.example.presentation.databinding.ActivityProductDetailBinding
import com.example.presentation.databinding.ActivityReservationConfirmBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservationConfirmActivity : AppCompatActivity() {
    private val viewModel: ReservationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityReservationConfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.load()
        
    }
}