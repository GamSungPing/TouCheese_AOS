package com.example.presentation.screen.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.presentation.main.view.MainActivity
import com.example.presentation.screen.splash.nav.SplashNavHost
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val startDestination = intent.getStringExtra("startDestination")

        setContent {
            SplashNavHost(
                UserApiClient.instance,
                startDestination,
                navigateToMain = {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                },
                onClickClose = { finish() }
            )
        }
    }
}