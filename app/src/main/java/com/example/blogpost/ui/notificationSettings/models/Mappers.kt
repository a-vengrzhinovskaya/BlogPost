package com.example.blogpost.ui.notificationSettings.models

import com.example.blogpost.domain.settings.models.Setting

fun Setting.toUI() = SettingUI(
    settingName = name,
    description = description,
    isEnabled = isEnabled
)