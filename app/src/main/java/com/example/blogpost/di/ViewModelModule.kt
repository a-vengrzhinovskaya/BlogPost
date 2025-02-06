package com.example.blogpost.di

import com.example.blogpost.ui.auth.AuthViewModel
import com.example.blogpost.ui.feed.FeedViewModel
import com.example.blogpost.ui.postDetails.PostDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::AuthViewModel)
    viewModelOf(::FeedViewModel)
    viewModelOf(::PostDetailsViewModel)
}