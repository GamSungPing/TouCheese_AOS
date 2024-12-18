package com.example.presentation.studio

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.presentation.main.view.MainActivity
import com.example.presentation.studio.navigation.StudioNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val studioId = intent.getStringExtra("studioId") ?: ""
        val profileURL = intent.getStringExtra("profileURL") ?: ""

        setContent {
            StudioNavHost(
                studioId =  studioId,
                profileURL = profileURL,
                onClickBack = { finish() },
                navigateToSchedule = {
                    startActivity(Intent(this, MainActivity::class.java))
                }
            )
        }
    }
}