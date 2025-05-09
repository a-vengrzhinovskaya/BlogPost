package com.example.blogpost.di

import com.example.blogpost.ui.auth.AuthViewModel
import com.example.blogpost.ui.feed.FeedViewModel
import com.example.blogpost.ui.notificationSettings.NotificationSettingsViewModel
import com.example.blogpost.ui.postDetails.PostDetailsViewModel
import com.example.blogpost.ui.postEditor.PostEditorViewModel
import com.example.blogpost.ui.profile.ProfileViewModel
import com.example.blogpost.ui.settings.SettingsMenuViewModel
import com.example.blogpost.ui.singUp.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::AuthViewModel)
    viewModelOf(::FeedViewModel)
    viewModelOf(::PostDetailsViewModel)
    viewModelOf(::PostEditorViewModel)
    viewModelOf(::SettingsMenuViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::NotificationSettingsViewModel)
    viewModelOf(::SignUpViewModel)
}