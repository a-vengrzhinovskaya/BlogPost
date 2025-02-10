package com.example.blogpost.di

import com.example.blogpost.domain.comments.GetCommentsWithAuthorByPostIdUseCase
import com.example.blogpost.domain.posts.GetPostsWithAuthorUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPostsWithAuthorUseCase(get(), get()) }
    factory { GetCommentsWithAuthorByPostIdUseCase(get(), get(), get()) }
}