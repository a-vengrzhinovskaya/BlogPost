package com.example.blogpost.ui.auth

import com.example.blogpost.ui.common.StateViewModel
import kotlinx.coroutines.flow.update

class AuthViewModel(

) : StateViewModel<AuthScreenState>(AuthScreenState()) {
    fun onEmailFieldValueChange(newValue: String) =
        mutableState.update { it.copy(email = newValue) }

    fun onPasswordFieldValueChange(newValue: String) =
        mutableState.update { it.copy(password = newValue) }
}