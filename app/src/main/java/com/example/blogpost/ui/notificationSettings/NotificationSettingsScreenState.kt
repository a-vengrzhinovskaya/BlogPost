package com.example.blogpost.ui.notificationSettings

import com.example.blogpost.ui.notificationSettings.models.SettingUI

data class NotificationSettingsScreenState(
    val settings: List<Pair<SettingUI, Boolean>> = emptyList()
)