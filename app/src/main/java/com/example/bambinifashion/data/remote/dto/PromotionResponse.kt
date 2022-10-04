package com.example.bambinifashion.data.remote.dto

import com.example.bambinifashion.data.Mapper
import com.example.bambinifashion.domain.models.Promotion
import com.google.gson.annotations.SerializedName

data class PromotionResponse(
    @SerializedName("user") val user: UserResponse?
) : Mapper<List<Promotion>> {
    override fun toDomain(): List<Promotion> {
        return user?.proline?.center?.items?.map { it.toDomain() } ?: listOf()
    }
}

data class UserResponse(
    @SerializedName("proline") val proline: ProlineResponse?
)

data class ProlineResponse(
    @SerializedName("center") val center: CenterResponse?,
    @SerializedName("left") val left: String?,
    @SerializedName("right") val right: String?
)

data class ItemResponse(
    @SerializedName("content") val content: String?,
    @SerializedName("countdown") val countdown: CountdownResponse?,
    @SerializedName("duration") val duration: Long?,
    @SerializedName("highlight") val highlight: HighlightResponse?
) : Mapper<Promotion> {
    override fun toDomain(): Promotion = Promotion(
        content = content ?: "",
        duration = duration ?: Promotion.default_duration,
        periodicity = highlight?.periodicity ?: Promotion.default_periodicity,
        backgroundColor = highlight?.backgroundColor ?: Promotion.default_background_color,
        textColor = highlight?.textColor ?: Promotion.default_text_color,
    )
}

data class HighlightResponse(
    @SerializedName("backgroundColor") val backgroundColor: String?,
    @SerializedName("periodicity") val periodicity: Int?,
    @SerializedName("textColor") val textColor: String?
)

data class CountdownResponse(
    @SerializedName("content") val content: PromotionContentResponse?,
    @SerializedName("hasGlitch") val hasGlitch: Boolean?,
    @SerializedName("isCountHidden") val isCountHidden: Boolean?,
    @SerializedName("to") val to: String?
)

data class PromotionContentResponse(
    @SerializedName("activeAfter") val activeAfter: String?,
    @SerializedName("activeBefore") val activeBefore: String?,
    @SerializedName("finished") val finished: String?
)

data class CenterResponse(
    @SerializedName("items") val items: List<ItemResponse>?
)