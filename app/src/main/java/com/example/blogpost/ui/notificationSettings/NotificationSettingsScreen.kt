package com.example.blogpost.ui.notificationSettings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.blogpost.ui.common.components.PrimaryTopBar
import com.example.blogpost.ui.notificationSettings.components.NotificationSettingItem
import com.example.blogpost.ui.theme.extraLargeDp
import com.example.blogpost.ui.theme.mediumDp
import org.koin.androidx.compose.koinViewModel

class NotificationSettingsScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<NotificationSettingsViewModel>()
        val state by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) { viewModel.fetchSettings() }

        Scaffold(
            contentWindowInsets = WindowInsets(0.dp),
            topBar = {
                PrimaryTopBar(
                    text = "Настройки уведомлений",
                    onBackClick = remember { { navigator.pop() } }
                )
            },
            content = { paddingValues ->
                NotificationSettingsScreenBody(
                    paddingValues = paddingValues,
                    state = state,
                    onNotificationToggle = remember { viewModel::onSettingSwitched }
                )
            }
        )
    }
}

@Composable
private fun NotificationSettingsScreenBody(
    paddingValues: PaddingValues,
    state: NotificationSettingsScreenState,
    onNotificationToggle: (String, Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(extraLargeDp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(mediumDp)
    ) {
        state.settings.forEach {
            NotificationSettingItem(
                settingName = it.first.settingName,
                settingDescription = it.first.description,
                isSettingEnabled = it.second,
                onToggle = onNotificationToggle
            )
        }
    }
}