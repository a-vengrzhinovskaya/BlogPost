package com.example.blogpost

import android.app.Application
import com.example.blogpost.di.networkModule
import com.example.blogpost.di.repositoryModule
import com.example.blogpost.di.useCaseModule
import com.example.blogpost.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}