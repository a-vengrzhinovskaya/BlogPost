package com.example.blogpost.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val BLOGPOST_SETTINGS = "blogpost_settings"

val sharedPreferencesModule = module {
    single {
        getSharedPreferences(androidApplication())
    }
    single<SharedPreferences.Editor> {
        getSharedPreferences(androidApplication()).edit()
    }
}

fun getSharedPreferences(app: Application): SharedPreferences =
    app.getSharedPreferences(BLOGPOST_SETTINGS, Context.MODE_PRIVATE)