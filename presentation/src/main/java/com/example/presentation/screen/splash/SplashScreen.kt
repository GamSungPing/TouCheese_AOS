package com.example.presentation.screen.splash

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.Activation
import com.example.presentation.R
import com.example.presentation.main.view.MainActivity
import com.example.presentation.screen.splash.vm.SplashViewModel
import com.example.presentation.theme.primary01
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToLogin: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.activation.collect {
            when(it){
                Activation.Loading -> Unit
                Activation.Activate -> context.startActivity(
                    Intent(context, MainActivity::class.java)
                )
                Activation.DeActivate -> {
                    delay(1_500)
                    navigateToLogin()
                }
            }
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(color = primary01)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.touchess_logi),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .size(270.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    SplashScreen(
        navigateToLogin = {}
    )
}