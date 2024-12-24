package com.example.presentation.screen.profile

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.R
import com.example.presentation.calendar.DateSelectButton
import com.example.presentation.component.ModifyNickNameModal
import com.example.presentation.login.LoginActivity
import com.example.presentation.screen.profile.vm.ProfileViewModel
import com.example.presentation.screen.profile.vm.model.OptionHeader
import com.example.presentation.screen.profile.vm.model.Options
import com.example.presentation.screen.profile.vm.model.SettingsOption
import com.example.presentation.theme.gray03
import com.example.presentation.theme.gray10

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val loggedIn = viewModel.loggedIn.collectAsStateWithLifecycle()
    val memberName = viewModel.memberName.collectAsStateWithLifecycle()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ProfileScreen(
        isLoggedIn = loggedIn.value,
        userNickName = memberName.value,
        onCompleteModifyNickname = { nickName ->
            viewModel.modifyNickName(nickName)
        },
        requestLogin = {
            context.startActivity(Intent(context, LoginActivity::class.java))
        },
        requestLogout = {
            viewModel.logout()
        },
        requestWithdraw = {
            viewModel.withdraw()
        }
    )
}

@Composable
fun ProfileScreen(
    isLoggedIn: Boolean,
    userNickName: String,
    onCompleteModifyNickname: (String) -> Unit,
    requestLogin: () -> Unit,
    requestLogout: () -> Unit,
    requestWithdraw: () -> Unit,
) {
    var selectedOption: Options? by remember { mutableStateOf(null) }

    when (selectedOption) {
        OptionHeader.NicknameEdit -> {
            ModifyNickNameModal(
                nickName = userNickName,
                onComplete = { nickName ->
                    onCompleteModifyNickname(nickName)
                    selectedOption = null
                },
                onDismiss = {
                    selectedOption = null
                }
            )
        }

        OptionHeader.RequireLogin -> {
            requestLogin()
        }
        SettingsOption.ContactEmail -> {}
        SettingsOption.TermsAndPolicy -> {}
        SettingsOption.OpenSourceLicense -> {}
        SettingsOption.ClearCache -> {}
        SettingsOption.AppVersion -> {}
        SettingsOption.Logout -> {
            requestLogout()
        }

        SettingsOption.WithdrawMembership -> {
            requestWithdraw()
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "내 정보",
                fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                fontSize = 20.sp,
            )
        }

        item {
            if (isLoggedIn) {
                Text(
                    text = "${userNickName}님, 반가워요! ",
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    fontSize = 20.sp,
                    color = gray10,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .padding(20.dp)
                )
                ProfileItemSection(option = OptionHeader.NicknameEdit) { newOption ->
                    selectedOption = newOption
                }
            } else {
                DateSelectButton(
                    title = OptionHeader.RequireLogin.title,
                    clickable = true,
                    onClick = {

                    },
                    modifier = Modifier.padding(30.dp)
                )
            }
        }

        enumValues<SettingsOption>().forEach { option ->
            item {
                ProfileItemSection(option = option) { newOption ->
                    selectedOption = newOption
                }
            }
        }
    }
}

@Composable
fun ProfileItemSection(
    option: Options,
    onClick: (Options) -> Unit
) {
    Column(
        modifier = Modifier.clickable { onClick(option) }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = option.title,
                fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                fontSize = 16.sp,
                modifier = Modifier
            )

            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_move_foward),
                contentDescription = "arrowForward",
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(
            Modifier
                .height(
                    when (option) {
                        OptionHeader.NicknameEdit,
                        SettingsOption.ContactEmail -> 9.dp

                        else -> 1.dp
                    }
                )
                .fillMaxWidth()
                .background(gray03)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileScreenPreview() {
    ProfileScreen(
        isLoggedIn = true,
        userNickName = "불꽃남자김인직",
        onCompleteModifyNickname = {},
        requestLogout = {},
        requestWithdraw = {},
        requestLogin = {

        }
    )
}