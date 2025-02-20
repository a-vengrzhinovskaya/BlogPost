package com.example.blogpost.domain.settings.models

data class Setting(
    val name: String,
    val description: String = "",
    val isEnabled: Boolean
)