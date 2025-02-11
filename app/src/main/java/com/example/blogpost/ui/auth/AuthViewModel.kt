package com.example.blogpost.ui.auth

import androidx.lifecycle.viewModelScope
import com.example.blogpost.domain.users.UsersRepository
import com.example.blogpost.ui.common.StateViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val usersRepository: UsersRepository
) : StateViewModel<AuthScreenState>(AuthScreenState()) {
    fun login() {
        viewModelScope.launch {
            usersRepository.login(
                state.value.email,
                state.value.password
            )
        }
    }

    fun onEmailFieldValueChange(newValue: String) =
        mutableState.update { it.copy(email = newValue) }

    fun onPasswordFieldValueChange(newValue: String) =
        mutableState.update { it.copy(password = newValue) }
}