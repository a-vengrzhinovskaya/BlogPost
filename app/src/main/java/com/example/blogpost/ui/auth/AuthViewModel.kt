package com.example.blogpost.ui.auth

import android.util.Log
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
            try {
                usersRepository.login(
                    state.value.email,
                    state.value.password
                )
                mutableState.update { it.copy(isAuthorizationSuccessful = true) }
            } catch (e: Exception) {
                Log.d("auth", e.message.toString())
            }
        }
    }

    fun onEmailValueChange(newValue: String) =
        mutableState.update { it.copy(email = newValue) }

    fun onPasswordValueChange(newValue: String) =
        mutableState.update { it.copy(password = newValue) }
}