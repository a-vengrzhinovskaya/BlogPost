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
import com.example.blogpost.ui.auth.AuthScreen
import com.example.blogpost.ui.common.components.LargeSpacer
import com.example.blogpost.ui.common.components.MediumSpacer
import com.example.blogpost.ui.common.components.PrimaryButton
import com.example.blogpost.ui.feed.FeedScreen
import com.example.blogpost.ui.settings.components.SettingsItem
import com.example.blogpost.ui.settings.components.SettingsTopBar
import com.example.blogpost.ui.theme.extraLargeDp
import org.koin.androidx.compose.koinViewModel

class SettingsScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<SettingsViewModel>()
        val state by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) {
            viewModel.fetchSettings()
            viewModel.checkIfAuthorized()
        }

        val wasAuthorized by remember { mutableStateOf(state.isAuthorized) }
        LaunchedEffect(state.isAuthorized) {
            if (wasAuthorized != state.isAuthorized && !state.isAuthorized) navigator.pop()
        }

        Scaffold(
            contentWindowInsets = WindowInsets(0.dp),
            topBar = {
                SettingsTopBar(
                    onBackClick = remember {
                        {
                            navigator.pop()
                            navigator.push(AuthScreen())
                        }
                    }
                )
            },
            content = { paddingValues ->
                SettingsScreenBody(
                    paddingValues = paddingValues,
                    state = state,
                    onGoToProfileClick = remember { { navigator.push(FeedScreen()) } },
                    onGoToNotificationSettingsClick = remember { { navigator.push(FeedScreen()) } },
                    onDeleteAccountClick = remember { { viewModel.deleteAccount() } },
                    onLogoutCLick = remember { { viewModel.logOut() } },
                    onLoginClick = remember { { navigator.push(AuthScreen()) } }
                )
            }
        )
    }
}

@Composable
private fun SettingsScreenBody(
    paddingValues: PaddingValues,
    state: SettingsScreenState,
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
            with(state) {
                SettingsItem(
                    settingName = profileSettings.first().settingName,
                    settingIconId = profileSettings.first().settingIconId,
                    navigatesToScreen = profileSettings.first().navigatesToScreen,
                    onSettingClick = onGoToProfileClick
                )
                MediumSpacer()
                SettingsItem(
                    settingName = profileSettings[1].settingName,
                    settingIconId = profileSettings[1].settingIconId,
                    navigatesToScreen = profileSettings[1].navigatesToScreen,
                    onSettingClick = onGoToNotificationSettingsClick
                )
                MediumSpacer()
                SettingsItem(
                    settingName = profileSettings[2].settingName,
                    settingIconId = profileSettings[2].settingIconId,
                    navigatesToScreen = profileSettings[2].navigatesToScreen,
                    onSettingClick = onDeleteAccountClick
                )
                MediumSpacer()
                SettingsItem(
                    settingName = profileSettings[3].settingName,
                    settingIconId = profileSettings[3].settingIconId,
                    navigatesToScreen = profileSettings[3].navigatesToScreen,
                    onSettingClick = onLogoutCLick
                )
            }
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