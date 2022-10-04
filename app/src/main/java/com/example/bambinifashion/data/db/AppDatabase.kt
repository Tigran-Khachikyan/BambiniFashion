package com.example.bambinifashion.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bambinifashion.data.db.entities.CategoryDto
import com.example.bambinifashion.data.db.entities.ContentDto
import com.example.bambinifashion.data.db.entities.PromotionDto

@Database(entities = [ContentDto::class, CategoryDto::class, PromotionDto::class], version = 1)
@TypeConverters(CategoryConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val contentDao: ContentDao
    abstract val promotionDao: PromotionDao

    companion object {
        fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "App_database")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}