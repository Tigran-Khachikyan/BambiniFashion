package com.example.bambinifashion.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bambinifashion.data.Mapper
import com.example.bambinifashion.domain.models.Content

@Entity(tableName = "CONTENT")
data class ContentDto(
    @ColumnInfo(name = "TITLE")
    var title: String? = null,

    @ColumnInfo(name = "URL")
    var bannerUrl: String? = null,

    @ColumnInfo(name = "CATEGORIES")
    var categories: List<CategoryDto>? = null
) : Mapper<Content> {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    var id: Long = 0

    override fun toDomain(): Content {
        return Content(
            title = this.title,
            bannerUrl = this.bannerUrl,
            categories = this.categories?.map { it.toDomain() }
        )
    }
}