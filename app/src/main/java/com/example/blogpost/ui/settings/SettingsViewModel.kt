package com.example.blogpost.ui.settings

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.blogpost.R
import com.example.blogpost.domain.users.UsersRepository
import com.example.blogpost.ui.common.StateViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val usersRepository: UsersRepository
) : StateViewModel<SettingsScreenState>(SettingsScreenState()) {
    init {
        viewModelScope.launch {
            mutableState.update {
                it.copy(
                    isAuthorized = usersRepository.isAuthorized()
                )
            }
        }
    }

    fun fetchSettings() = try {
        viewModelScope.launch {
            mutableState.update {
                it.copy( // TODO:  move to repo
                    profileSettings = listOf(
                        SettingsScreenState.ProfileSetting(
                            settingName = "Профиль",
                            settingIconId = R.drawable.ic_user,
                            navigatesToScreen = true
                        ),
                        SettingsScreenState.ProfileSetting(
                            settingName = "Уведомления",
                            settingIconId = R.drawable.ic_notifications,
                            navigatesToScreen = true
                        ),
                        SettingsScreenState.ProfileSetting(
                            settingName = "Удалить акканут",
                            settingIconId = R.drawable.ic_delete_account
                        ),
                        SettingsScreenState.ProfileSetting(
                            settingName = "Выход",
                            settingIconId = R.drawable.ic_logout
                        )
                    )
                )
            }
        }
    } catch (e: Exception) {
        Log.d("settings", "Attempt to fetch settings: ${e.message.toString()}")
    }

    fun logOut() = try {
        viewModelScope.launch {
            usersRepository.logOut()
            mutableState.update { it.copy(isAuthorized = false) }
        }
    } catch (e: Exception) {
        Log.d("settings", "Attempt to log out: ${e.message.toString()}")
    }

    fun deleteAccount() = try {
        viewModelScope.launch {
            usersRepository.deleteUser()
            mutableState.update { it.copy(isAuthorized = false) }
        }
    } catch (e: Exception) {
        Log.d("settings", "Attempt to delete user: ${e.message.toString()}")
    }
}