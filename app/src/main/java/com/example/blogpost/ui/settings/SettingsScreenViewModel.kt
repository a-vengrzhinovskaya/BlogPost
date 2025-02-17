package com.example.blogpost.ui.settings

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.blogpost.R
import com.example.blogpost.domain.users.UsersRepository
import com.example.blogpost.ui.common.StateViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsScreenViewModel(
    private val usersRepository: UsersRepository
) : StateViewModel<SettingScreenState>(SettingScreenState()) {
    init {
        checkIfAuthorized()
    }

    fun fetchSettings() = try {
        viewModelScope.launch {
            mutableState.update {
                it.copy( // TODO:  move to repo
                    profileSettings = listOf(
                        SettingScreenState.ProfileSetting(
                            settingName = "Профиль",
                            settingIconId = R.drawable.ic_user,
                            navigatesToScreen = true
                        ),
                        SettingScreenState.ProfileSetting(
                            settingName = "Уведомления",
                            settingIconId = R.drawable.ic_notifications,
                            navigatesToScreen = true
                        ),
                        SettingScreenState.ProfileSetting(
                            settingName = "Удалить акканут",
                            settingIconId = R.drawable.ic_delete_account
                        ),
                        SettingScreenState.ProfileSetting(
                            settingName = "Выход",
                            settingIconId = R.drawable.ic_logout
                        )
                    )
                )
            }
        }
        Log.d("settings", state.value.profileSettings.toString())
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
        mutableState.update { it.copy(isAuthorized = false) } // TODO: used for test. needs to be removed.
    }

    private fun checkIfAuthorized() = viewModelScope.launch {
        mutableState.update {
            it.copy(
                isAuthorized = (usersRepository.getCurrentUser().first() != null)
            )
        }
    }
}