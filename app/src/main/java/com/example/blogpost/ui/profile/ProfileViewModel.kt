package com.example.blogpost.ui.profile

import androidx.lifecycle.viewModelScope
import com.example.blogpost.domain.users.UsersRepository
import com.example.blogpost.ui.common.StateViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(private val usersRepository: UsersRepository) :
    StateViewModel<ProfileScreenState>(ProfileScreenState()) {
    fun fetchUser() {
        viewModelScope.launch {
            usersRepository.getCurrentUser()?.let { user ->
                mutableState.update {
                    it.copy(
                        name = user.name,
                        email = user.email,
                        avatarUrl = user.avatarUrl
                    )
                }
            }
        }
    }

    fun onNameFieldValueChange(newValue: String) =
        mutableState.update { it.copy(name = newValue) }

    fun onEmailFieldValueChange(newValue: String) =
        mutableState.update { it.copy(email = newValue) }

    fun onAvatarChange(newValue: String) =
        mutableState.update { it.copy(avatarUrl = newValue) } // TODO: upload image

    fun updateProfile(
        name: String = state.value.name,
        email: String = state.value.email,
        avatarUrl: String = state.value.avatarUrl
    ) {
        mutableState.update {
            it.copy(
                name = name,
                email = email,
                avatarUrl = avatarUrl
            )
        }
    }
}