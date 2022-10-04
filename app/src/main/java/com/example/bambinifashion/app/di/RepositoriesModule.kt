package com.example.bambinifashion.app.di

import com.example.bambinifashion.data.repositories.ContentRepositoryImpl
import com.example.bambinifashion.data.repositories.PromotionRepositoryImpl
import com.example.bambinifashion.domain.repositories.ContentRepository
import com.example.bambinifashion.domain.repositories.PromotionRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoriesModule = module {
    single<ContentRepository> {
        ContentRepositoryImpl(
            api = get(),
            dao = get(),
            coroutineContext = Dispatchers.IO
        )
    }
    single<PromotionRepository> {
        PromotionRepositoryImpl(
            api = get(),
            dao = get(),
            coroutineContext = Dispatchers.IO
        )
    }
}
