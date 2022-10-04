package com.example.bambinifashion.domain.repositories

import com.example.bambinifashion.domain.Request
import com.example.bambinifashion.domain.models.Content

interface ContentRepository {
    suspend fun getContent(): Request<List<Content>>

    suspend fun saveContent(items: List<Content>)

    suspend fun getSavedContent(): List<Content>?
}