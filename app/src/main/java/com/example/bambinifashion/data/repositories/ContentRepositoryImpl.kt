package com.example.bambinifashion.data.repositories

import com.example.bambinifashion.data.db.ContentDao
import com.example.bambinifashion.data.db.entities.CategoryDto
import com.example.bambinifashion.data.db.entities.ContentDto
import com.example.bambinifashion.data.remote.ServiceApi
import com.example.bambinifashion.domain.Request
import com.example.bambinifashion.domain.models.Content
import com.example.bambinifashion.domain.repositories.ContentRepository
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class ContentRepositoryImpl(
    private val api: ServiceApi,
    private val dao: ContentDao,
    private val coroutineContext: CoroutineContext
) : ContentRepository {
    override suspend fun getContent(): Request<List<Content>> {
        return withContext(coroutineContext) {
            try {
                val response = api.getContent()
                if (response.isSuccessful) {
                    Request.Success(response.body()?.toDomain() ?: listOf())
                } else {
                    Request.Error(response.code())
                }
            } catch (ex: Exception) {
                Request.Error(null)
            }
        }
    }

    override suspend fun saveContent(items: List<Content>) {
        withContext(coroutineContext) {
            if (dao.getContent().isNullOrEmpty()) {
                items.forEach {
                    dao.saveContent(ContentDto(
                        title = it.title,
                        bannerUrl = it.bannerUrl,
                        categories = it.categories?.map { cat ->
                            CategoryDto(
                                imageUrl = cat.imageUrl,
                                backgroundColor = cat.backgroundColor,
                                title = cat.title
                            )
                        }
                    ))
                }
            }
        }
    }

    override suspend fun getSavedContent(): List<Content>? {
        return dao.getContent()?.map {
            it.toDomain()
        }
    }
}