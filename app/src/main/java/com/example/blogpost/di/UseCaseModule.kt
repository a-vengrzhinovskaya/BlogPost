package com.example.blogpost.di

import com.example.blogpost.domain.comments.GetCommentsByPostIdUseCase
import com.example.blogpost.domain.posts.GetPostsWithAuthorUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::GetCommentsByPostIdUseCase)
    factory { GetPostsWithAuthorUseCase(get(), get()) }
}