package com.example.blogpost.di

import com.example.blogpost.domain.comments.CreateCurrentUserCommentUseCase
import com.example.blogpost.domain.comments.GetCommentsWithAuthorByPostIdUseCase
import com.example.blogpost.domain.posts.GetPostsWithAuthorUseCase
import com.example.blogpost.domain.posts.LikePostUseCase
import com.example.blogpost.domain.posts.PublishCurrentUserPostUseCase
import com.example.blogpost.domain.users.GetCurrentUserPostsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPostsWithAuthorUseCase(get(), get()) }
    factory { GetCommentsWithAuthorByPostIdUseCase(get(), get(), get()) }
    factory { GetCurrentUserPostsUseCase(get(), get()) }
    factory { CreateCurrentUserCommentUseCase(get(), get()) }
    factory { PublishCurrentUserPostUseCase(get(), get()) }
    factory { LikePostUseCase(get(), get()) }
}