package com.example.blogpost.domain.settings

import com.example.blogpost.domain.settings.models.Setting

interface SettingsRepository {
    suspend fun getNotificationSettings(): List<Setting>

    fun saveNotificationSetting(settingName: String, isEnabled: Boolean)
}