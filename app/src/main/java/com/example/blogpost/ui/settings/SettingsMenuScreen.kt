package com.example.blogpost.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.blogpost.R
import com.example.blogpost.ui.auth.AuthScreen
import com.example.blogpost.ui.common.components.LargeSpacer
import com.example.blogpost.ui.common.components.MediumSpacer
import com.example.blogpost.ui.common.components.PrimaryButton
import com.example.blogpost.ui.common.components.PrimaryTopBar
import com.example.blogpost.ui.notificationSettings.NotificationSettingsScreen
import com.example.blogpost.ui.profile.ProfileScreen
import com.example.blogpost.ui.settings.components.SettingsItem
import com.example.blogpost.ui.theme.extraLargeDp
import org.koin.androidx.compose.koinViewModel

class SettingsMenuScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<SettingsMenuViewModel>()
        val state by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) { viewModel.checkIfAuthorized() }

        val wasAuthorized by remember { mutableStateOf(state.isAuthorized) }
        LaunchedEffect(state.isAuthorized) {
            if (wasAuthorized != state.isAuthorized && !state.isAuthorized) navigator.replaceAll(
                AuthScreen()
            )
        }

        Scaffold(
            contentWindowInsets = WindowInsets(0.dp),
            topBar = {
                PrimaryTopBar(
                    text = "Настройки",
                    onBackClick = remember { { navigator.pop() } }
                )
            },
            content = { paddingValues ->
                SettingsScreenBody(
                    paddingValues = paddingValues,
                    state = state,
                    onGoToProfileClick = remember { { navigator.push(ProfileScreen()) } },
                    onGoToNotificationSettingsClick = remember { { navigator.push(NotificationSettingsScreen()) } },
                    onDeleteAccountClick = remember { { viewModel.deleteAccount() } },
                    onLogoutCLick = remember { { viewModel.logOut() } },
                    onLoginClick = remember { { navigator.replaceAll(AuthScreen()) } }
                )
            }
        )
    }
}

@Composable
private fun SettingsScreenBody(
    paddingValues: PaddingValues,
    state: SettingsMenuScreenState,
    onGoToProfileClick: () -> Unit,
    onGoToNotificationSettingsClick: () -> Unit,
    onDeleteAccountClick: () -> Unit,
    onLogoutCLick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(extraLargeDp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (state.isAuthorized) {
            SettingsItem(
                settingName = "Профиль",
                settingIconId = R.drawable.ic_user,
                navigatesToScreen = true,
                onSettingClick = onGoToProfileClick
            )
            MediumSpacer()
            SettingsItem(
                settingName = "Уведомления",
                settingIconId = R.drawable.ic_notifications,
                navigatesToScreen = true,
                onSettingClick = onGoToNotificationSettingsClick
            )
            MediumSpacer()
            SettingsItem(
                settingName = "Удалить акканут",
                settingIconId = R.drawable.ic_delete_account,
                onSettingClick = onDeleteAccountClick
            )
            MediumSpacer()
            SettingsItem(
                settingName = "Выход",
                settingIconId = R.drawable.ic_logout,
                onSettingClick = onLogoutCLick
            )
        } else {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Авторизуйтесь, чтобы настраивать свой профиль",
                textAlign = TextAlign.Center
            )
            LargeSpacer()
            PrimaryButton(
                text = "Войти",
                onClick = onLoginClick
            )
        }
    }
}