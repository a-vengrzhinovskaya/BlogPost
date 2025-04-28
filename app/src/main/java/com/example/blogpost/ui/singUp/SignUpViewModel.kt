package com.example.blogpost.ui.singUp

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.blogpost.domain.users.UsersRepository
import com.example.blogpost.ui.common.StateViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val usersRepository: UsersRepository
) : StateViewModel<SignUpScreenState>(SignUpScreenState()) {
    fun onNameValueChange(newValue: String) =
        mutableState.update { it.copy(name = newValue) }

    fun onEmailValueChange(newValue: String) =
        mutableState.update { it.copy(email = newValue) }

    fun onPasswordValueChange(newValue: String) =
        mutableState.update { it.copy(password = newValue) }

    fun onConfirmPasswordValueChange(newValue: String) =
        mutableState.update { it.copy(confirmPassword = newValue) }

    fun signUp() = try {
        viewModelScope.launch {
            usersRepository.createUser(
                state.value.name,
                state.value.email,
                state.value.password,
                state.value.confirmPassword
            )
            usersRepository.login(state.value.email, state.value.password)
            mutableState.update { it.copy(isSignUpComplete = true) }
        }
    } catch (e: Exception) {
        Log.d("signUp", "Attempt to sign up: ${e.message.toString()}")
    }
}