package com.example.blogpost.ui.singUp

data class SignUpScreenState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isSignUpComplete: Boolean = false
)