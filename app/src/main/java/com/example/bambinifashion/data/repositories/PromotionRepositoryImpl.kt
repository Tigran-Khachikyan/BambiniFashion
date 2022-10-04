package com.example.bambinifashion.data.repositories

import com.example.bambinifashion.data.db.PromotionDao
import com.example.bambinifashion.data.db.entities.PromotionDto
import com.example.bambinifashion.data.remote.ServiceApi
import com.example.bambinifashion.domain.Request
import com.example.bambinifashion.domain.models.Promotion
import com.example.bambinifashion.domain.repositories.PromotionRepository
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class PromotionRepositoryImpl(
    private val api: ServiceApi,
    private val dao: PromotionDao,
    private val coroutineContext: CoroutineContext
) : PromotionRepository {
    override suspend fun getPromotions(): Request<List<Promotion>> {
        return withContext(coroutineContext) {
            try {
                val response = api.getPromotion()
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

    override suspend fun savePromotions(items: List<Promotion>) {
        withContext(coroutineContext) {
            if (dao.getPromotions().isNullOrEmpty()) {
                items.forEach {
                    dao.savePromotion(
                        PromotionDto(
                            content = it.content,
                            duration = it.duration,
                            periodicity = it.periodicity,
                            backgroundColor = it.backgroundColor,
                            textColor = it.textColor
                        )
                    )
                }
            }
        }
    }

    override suspend fun getSavedPromotions(): List<Promotion>? {
        return dao.getPromotions()?.map {
            it.toDomain()
        }
    }
}