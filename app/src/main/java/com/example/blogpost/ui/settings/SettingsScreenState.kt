package com.example.blogpost.ui.settings

import com.example.blogpost.R

data class SettingsScreenState(
    val isAuthorized: Boolean = false,
    val profileSettings: List<ProfileSetting> = emptyList()
) {
    data class ProfileSetting(
        val settingName: String = "",
        val settingIconId: Int = R.drawable.ic_settings,
        val navigatesToScreen: Boolean = false
    )
}