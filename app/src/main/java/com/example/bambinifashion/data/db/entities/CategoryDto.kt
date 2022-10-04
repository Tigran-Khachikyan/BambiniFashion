package com.example.bambinifashion.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bambinifashion.data.Mapper
import com.example.bambinifashion.domain.models.Category

@Entity(tableName = "CATEGORY")
data class CategoryDto(
    @ColumnInfo(name = "URL")
    var imageUrl: String? = null,

    @ColumnInfo(name = "BACK_COLOR")
    var backgroundColor: String? = null,

    @ColumnInfo(name = "TITLE")
    var title: String? = null
) : Mapper<Category> {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    var id: Long = 0

    override fun toDomain(): Category {
        return Category(
            imageUrl = this.imageUrl,
            backgroundColor = this.backgroundColor,
            title = this.title
        )
    }
}

