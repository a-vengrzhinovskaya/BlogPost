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

private const val AUTH_TOKEN =
    "patweGxhro1cpktAS.38e4f064906684ee908f841df9ed898e818c7590581f8b58ce0a2f70132c94b5"
//TODO: убрать отсюда

val networkModule = module {
    val json = Json {
        ignoreUnknownKeys = true
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://api.airtable.com/v0/appPVJGE01IjRtB3M/")
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
                    .addHeader("Authorization", "Bearer $AUTH_TOKEN")
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    single<Converter.Factory> {
        json.asConverterFactory("application/json".toMediaType())
    }
}