package com.example.bambinifashion.app.di

import com.example.bambinifashion.data.remote.ApiProvider
import com.example.bambinifashion.data.remote.AuthorizationInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

val networkModule = module {
    single<Gson> { GsonBuilder().create() }
    single {
        HttpLoggingInterceptor().apply {
            level = (HttpLoggingInterceptor.Level.BODY)
        }
    }
    single { AuthorizationInterceptor() }
    single {
        ApiProvider(
            interceptors = listOf(
                get<HttpLoggingInterceptor>(),
                get<AuthorizationInterceptor>()
            ),
            gson = get()
        )
    }
    single { get<ApiProvider>().getMyServiceApi() }
}
