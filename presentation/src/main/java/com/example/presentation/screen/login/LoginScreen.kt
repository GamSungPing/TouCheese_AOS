package com.example.presentation.screen.login

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.R
import com.example.presentation.calendar.DateSelectButton
import com.example.presentation.component.KakaoLoginButton
import com.example.presentation.screen.login.vm.LoginEvent
import com.example.presentation.screen.login.vm.LoginViewModel
import com.example.presentation.main.view.MainActivity
import com.example.presentation.theme.primary01
import com.kakao.sdk.user.UserApiClient

@Composable
fun LoginScreen(
    apiClient: UserApiClient,
    onClickClose: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    when (uiState.value.loginEvent) {
        LoginEvent.LoginRequired -> Unit
        LoginEvent.Error -> {
            Toast.makeText(context, stringResource(R.string.text_login_fail), Toast.LENGTH_SHORT).show()
        }
        LoginEvent.Success,
        LoginEvent.LoggedIn -> {
            context.startActivity(Intent(context, MainActivity::class.java))
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
            Text(
                text = "X",
                fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopStart)
                    .clickable { onClickClose() }
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
            ) {

                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_logo_main),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                )
                Text(
                    text = stringResource(R.string.text_logo_message_top),
                    fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                    fontSize = 24.sp,
                )

                Text(
                    text = stringResource(R.string.text_logo_message_middle),
                    fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )

                Text(
                    text = stringResource(R.string.text_logo_message_bottom),
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )

                DateSelectButton(
                    title = "시작하기",
                    clickable = true,
                    onClick = {
                        viewModel.saveActivation()
                        context.startActivity(Intent(context, MainActivity::class.java))
                    }
                    , modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                )

                KakaoLoginButton(
                    apiClient = apiClient,
                    onSuccessLogin = { token ->
                        viewModel.login(token)
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                )
            }
        }
    }
}