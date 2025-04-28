package com.example.blogpost.di

import com.example.blogpost.data.repositories.comments.CommentsRepositoryImpl
import com.example.blogpost.data.repositories.posts.PostsRepositoryImpl
import com.example.blogpost.data.repositories.settings.SettingsRepositoryImpl
import com.example.blogpost.data.repositories.users.UsersRepositoryImpl
import com.example.blogpost.domain.comments.CommentsRepository
import com.example.blogpost.domain.posts.PostsRepository
import com.example.blogpost.domain.settings.SettingsRepository
import com.example.blogpost.domain.users.UsersRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UsersRepository> { UsersRepositoryImpl(get(), get()) }
    single<PostsRepository> { PostsRepositoryImpl(get()) }
    single<CommentsRepository> { CommentsRepositoryImpl(get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
}