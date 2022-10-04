package com.example.bambinifashion.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bambinifashion.data.db.entities.ContentDto

@Dao
interface ContentDao {

    @Query("SELECT * FROM CONTENT")
    suspend fun getContent(): List<ContentDto>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveContent(content: ContentDto)
}