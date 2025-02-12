package com.example.blogpost.ui.auth

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.blogpost.domain.users.UsersRepository
import com.example.blogpost.ui.common.StateViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val usersRepository: UsersRepository
) : StateViewModel<AuthScreenState>(AuthScreenState()) {
    fun login(onLoginSuccess: () -> Unit) {
        viewModelScope.launch {
            usersRepository.login(
                state.value.email,
                state.value.password
            ).catch { throwable ->
                Log.d("auth", throwable.message.toString())
            }.collect {
                onLoginSuccess()
            }
        }
    }

    fun onEmailFieldValueChange(newValue: String) =
        mutableState.update { it.copy(email = newValue) }

    fun onPasswordFieldValueChange(newValue: String) =
        mutableState.update { it.copy(password = newValue) }
}