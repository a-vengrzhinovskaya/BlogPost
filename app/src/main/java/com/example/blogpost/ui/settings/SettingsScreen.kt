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
import com.example.blogpost.ui.common.components.PrimaryButton
import com.example.blogpost.ui.feed.FeedScreen
import com.example.blogpost.ui.settings.components.SettingsItem
import com.example.blogpost.ui.theme.extraLargeDp
import org.koin.androidx.compose.koinViewModel

class SettingsScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<SettingsScreenViewModel>()
        val state by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        val settingsOnClickActions by remember {
            mutableStateOf(
                listOf<() -> Unit>(
                    { navigator.push(FeedScreen()) },
                    { navigator.push(FeedScreen()) },
                    { viewModel.deleteAccount() },
                    { viewModel.logOut() }
                )
            )
        }

        val settingsWithAction by remember {
            mutableStateOf(
                state.profileSettings.map {
                    Pair(it) {}
                }
            )
        }

        LaunchedEffect(state.profileSettings) {
            settingsWithAction.mapIndexed() { index, it ->
                it.copy(first = it.first, second = settingsOnClickActions[index])
            }
        }

        LaunchedEffect(state.isAuthorized) {
            if (state.isAuthorized) {
                viewModel.fetchSettings()
            }
        }

        Scaffold(
            contentWindowInsets = WindowInsets(0.dp),
            content = { paddingValues ->
                SettingsScreenBody(
                    paddingValues = paddingValues,
                    isAuthorized = state.isAuthorized,
                    settingsWithActions = settingsWithAction,
                    onLoginClick = remember { { navigator.push(AuthScreen()) } }
                )
            }
        )
    }
}

@Composable
private fun SettingsScreenBody(
    paddingValues: PaddingValues,
    isAuthorized: Boolean,
    settingsWithActions: List<Pair<SettingScreenState.ProfileSetting, () -> Unit>>,
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
        if (isAuthorized) {
            settingsWithActions.forEach {
                SettingsItem(
                    settingName = it.first.settingName,
                    settingIconId = it.first.settingIconId,
                    navigatesToScreen = it.first.navigatesToScreen,
                    onSettingClick = it.second
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