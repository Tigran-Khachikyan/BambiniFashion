package com.example.bambinifashion.domain.repositories

import com.example.bambinifashion.domain.Request
import com.example.bambinifashion.domain.models.Content
import com.example.bambinifashion.domain.models.Promotion

interface PromotionRepository {
    suspend fun getPromotions(): Request<List<Promotion>>

    suspend fun savePromotions(items: List<Promotion>)

    suspend fun getSavedPromotions(): List<Promotion>?
}