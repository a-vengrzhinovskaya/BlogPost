package com.example.blogpost.ui.notificationSettings

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.blogpost.domain.settings.SettingsRepository
import com.example.blogpost.ui.common.StateViewModel
import com.example.blogpost.ui.notificationSettings.models.toUI
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotificationSettingsViewModel(
    private val settingsRepository: SettingsRepository
) : StateViewModel<NotificationSettingsScreenState>(NotificationSettingsScreenState()) {
    fun fetchSettings() = viewModelScope.launch {
        try {
            mutableState.update { notificationSettingsScreenState ->
                notificationSettingsScreenState.copy(
                    settings = settingsRepository.getNotificationSettings()
                        .map { Pair(it.toUI(), it.isEnabled) }
                )
            }
        } catch (e: Exception) {
            Log.d("notifications", e.message.toString())
        }
    }

    fun onSettingSwitched(settingName: String, isEnabled: Boolean) {
        viewModelScope.launch {
            mutableState.update {
                it.copy(
                    settings = it.settings.map { setting ->
                        if (setting.first.settingName == settingName) Pair(
                            setting.first,
                            isEnabled
                        ) else setting
                    }
                )
            }
            saveSettings(settingName, isEnabled)
        }
    }

    private fun saveSettings(settingName: String, isEnabled: Boolean) = viewModelScope.launch {
        try {
            settingsRepository.saveNotificationSetting(settingName, isEnabled)
        } catch (e: Exception) {
            Log.d("notifications", e.message.toString())
        }
    }
}