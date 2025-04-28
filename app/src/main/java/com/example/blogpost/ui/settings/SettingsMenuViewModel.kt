package com.example.blogpost.ui.settings

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.blogpost.domain.users.UsersRepository
import com.example.blogpost.ui.common.StateViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsMenuViewModel(
    private val usersRepository: UsersRepository
) : StateViewModel<SettingsMenuScreenState>(SettingsMenuScreenState()) {
    fun checkIfAuthorized() = viewModelScope.launch {
        mutableState.update {
            it.copy(
                isAuthorized = usersRepository.isAuthorized()
            )
        }
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