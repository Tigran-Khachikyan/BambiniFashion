package com.example.bambinifashion.app.di

import com.example.bambinifashion.data.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbModule = module {
    single { AppDatabase.create(androidApplication()) }
    single { get<AppDatabase>().contentDao }
    single { get<AppDatabase>().promotionDao }
}