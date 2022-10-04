package com.example.bambinifashion.app

import android.app.Application
import com.example.bambinifashion.app.di.dbModule
import com.example.bambinifashion.app.di.networkModule
import com.example.bambinifashion.app.di.presentationModule
import com.example.bambinifashion.app.di.repositoriesModule
import com.example.bambinifashion.data.remote.ApiProvider
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
        initServiceProvider()
    }

    private fun initServiceProvider() {
        (get<ApiProvider>()).init()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    presentationModule,
                    repositoriesModule,
                    dbModule
                )
            )
        }
    }
}
