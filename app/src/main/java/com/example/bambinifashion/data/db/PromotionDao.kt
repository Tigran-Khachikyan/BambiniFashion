package com.example.bambinifashion.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bambinifashion.data.db.entities.ContentDto
import com.example.bambinifashion.data.db.entities.PromotionDto

@Dao
interface PromotionDao {

    @Query("SELECT * FROM PROMOTION")
    suspend fun getPromotions(): List<PromotionDto>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePromotion(promotion: PromotionDto)
}