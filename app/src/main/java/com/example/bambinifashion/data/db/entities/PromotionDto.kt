package com.example.bambinifashion.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bambinifashion.data.Mapper
import com.example.bambinifashion.domain.models.Promotion

@Entity(tableName = "PROMOTION")
data class PromotionDto(
    @ColumnInfo(name = "CONTENT") val content: String,

    @ColumnInfo(name = "DURATION") val duration: Long,

    @ColumnInfo(name = "PERIODICITY") val periodicity: Int,

    @ColumnInfo(name = "BACK_COLOR") val backgroundColor: String,

    @ColumnInfo(name = "TEXT_COLOR") val textColor: String
) : Mapper<Promotion> {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    var id: Long = 0

    override fun toDomain(): Promotion {
        return Promotion(
            content = this.content,
            duration = this.duration,
            periodicity = this.periodicity,
            backgroundColor = this.backgroundColor,
            textColor = this.textColor
        )
    }
}