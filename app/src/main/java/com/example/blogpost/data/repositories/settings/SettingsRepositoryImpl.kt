package com.example.blogpost.data.repositories.settings

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.example.blogpost.domain.settings.SettingsRepository
import com.example.blogpost.domain.settings.models.Setting
import com.example.blogpost.domain.settings.models.SettingType

private const val NOTIFICATIONS_DEFAULT_VALUE = true

class SettingsRepositoryImpl(private val sharedPreferences: SharedPreferences) :
    SettingsRepository {
    override suspend fun getNotificationSettings(): List<Setting> = SettingType.entries.map {
        Setting(
            name = it.settingName,
            description = it.description,
            isEnabled = sharedPreferences.getBoolean(it.key, NOTIFICATIONS_DEFAULT_VALUE)
        )
    }

    override fun saveNotificationSetting(settingName: String, isEnabled: Boolean) {
        try {
            sharedPreferences.edit {
                putBoolean(
                    SettingType.entries.first { it.settingName == settingName }.key,
                    isEnabled
                )
            }
        } catch (e: Exception) {
            Log.d("settings", e.message.toString())
        }
    }
}