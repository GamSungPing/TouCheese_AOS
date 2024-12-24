package com.example.presentation.component

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.theme.KakaoYellow
import com.example.presentation.theme.gray10
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

@Composable
fun KakaoLoginButton(
    apiClient: UserApiClient,
    onSuccessLogin: (idToken: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    TextButton(
        onClick = {
            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (token != null) {
                    apiClient.me { user, error ->
                        if (error != null) {
                            Log.e("KaKaoLoginLog", "사용자 정보 요청 실패", error)
                        }
                        else if (user != null) {
                            onSuccessLogin(user.id.toString())
                        }
                    }
                }
            }
            // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                    if (error != null) {
                        // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                        // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }

                        // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                        UserApiClient.instance.loginWithKakaoAccount(
                            context,
                            callback = callback
                        )
                    } else if (token != null) {
                        apiClient.me { user, error ->
                            if (error != null) {
                                Log.e("KaKaoLoginLog", "사용자 정보 요청 실패", error)
                            }
                            else if (user != null) {
                                onSuccessLogin(user.id.toString())
                            }
                        }
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.outlinedButtonColors(containerColor = KakaoYellow),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart,
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_kakao),
                contentDescription = null,
                modifier = Modifier.size(width = 20.dp, height = 20.dp),
                tint = Color.Black,
            )
            Text(
                stringResource(id = R.string.login_with_kakaotalk),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium,
                color = gray10,
                textAlign = TextAlign.Center,
            )
        }
    }
    fun getUser(){
        apiClient.me { user, error ->
            if (error != null) {
                Log.e("KaKaoLoginLog", "사용자 정보 요청 실패", error)
            }
            else if (user != null) {
                onSuccessLogin(user.id.toString())
            }
        }
    }
}
