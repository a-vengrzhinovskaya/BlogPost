package com.example.blogpost.ui.profile

data class ProfileScreenState(
    val name: String = "",
    val email: String = "",
    val avatarUrl: String = "",
    val isEditMode: Boolean = false
)
