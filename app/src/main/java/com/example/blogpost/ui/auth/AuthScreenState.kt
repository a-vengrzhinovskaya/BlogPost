package com.example.blogpost.ui.auth

data class AuthScreenState(
    val email: String = "",
    val password: String = "",
    val isAuthorizationSuccessful: Boolean = false
)