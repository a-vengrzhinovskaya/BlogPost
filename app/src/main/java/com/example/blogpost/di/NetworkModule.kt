package com.example.blogpost.di

import com.example.blogpost.data.network.BlogPostAPI
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit

val networkModule = module {
    val json = Json { ignoreUnknownKeys = true }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("http://176.197.19.218:666/api/")
            .addConverterFactory(get<Converter.Factory>())
            .client(get<OkHttpClient>())
            .build()
    }

    single<BlogPostAPI> {
        get<Retrofit>().create(BlogPostAPI::class.java)
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    single<Converter.Factory> {
        json.asConverterFactory("application/json".toMediaType())
    }
}