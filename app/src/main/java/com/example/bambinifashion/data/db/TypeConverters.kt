package com.example.bambinifashion.data.db

import androidx.room.TypeConverter
import com.example.bambinifashion.data.db.entities.CategoryDto
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


object CategoryConverter {
    @TypeConverter
    fun fromList(countryLang: List<CategoryDto?>?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<CategoryDto?>?>() {}.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun toList(countryLangString: String?): List<CategoryDto>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<CategoryDto?>?>() {}.type
        return gson.fromJson<List<CategoryDto>>(countryLangString, type)
    }
}