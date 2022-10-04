package com.example.bambinifashion.data.remote.dto

import com.example.bambinifashion.data.Mapper
import com.example.bambinifashion.domain.models.Category
import com.example.bambinifashion.domain.models.Content
import com.google.gson.annotations.SerializedName

data class ContentResponse(
    @SerializedName("page") val page: PageResponse?
) : Mapper<List<Content>> {
    override fun toDomain(): List<Content> {
        val result = arrayListOf<Content>()
        this.page?.content?.forEach { c ->
            c.data?.run {
                result.add(
                    Content(
                        title = title,
                        bannerUrl = image?.src,
                        categories = categories?.map { it.toDomain() }
                    )
                )
            }
        }
        return result
    }
}

data class PageResponse(
    @SerializedName("content") val content: List<CategoriesContentResponse>?,
    @SerializedName("meta") val meta: MetaResponse?
)

data class CategoriesContentResponse(
    @SerializedName("data") val data: DataResponse?,
    @SerializedName("name") val name: String?
)

data class MetaResponse(
    @SerializedName("description") val description: String?,
    @SerializedName("title") val title: String?
)

data class DataResponse(
    @SerializedName("caption") val caption: CaptionResponse?,
    @SerializedName("categories") val categories: List<CategoryResponse>?,
    @SerializedName("image") val image: ImageResponse?,
    @SerializedName("isMainImageRight") val isMainImageRight: Boolean?,
    @SerializedName("linkUrl") val linkUrl: String?,
    @SerializedName("size") val size: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("video") val video: VideoResponse?
)

data class CategoryResponse(
    @SerializedName("backgroundColor") val backgroundColor: String?,
    @SerializedName("image") val image: ImageResponse?,
    @SerializedName("linkUrl") val linkUrl: String?,
    @SerializedName("title") val title: String?
) : Mapper<Category> {
    override fun toDomain(): Category = Category(
        backgroundColor = this.backgroundColor,
        imageUrl = this.image?.src,
        title = this.title
    )
}

data class ImageResponse(
    @SerializedName("src") val src: String?
)

data class VideoResponse(
    @SerializedName("src") val src: String?
)

data class PositionResponse(
    @SerializedName("x") val x: String?,
    @SerializedName("y") val y: String?
)

data class HeadingResponse(
    @SerializedName("isHidden") val isHidden: Boolean?,
    @SerializedName("text") val text: String?
)

data class CtaResponse(
    @SerializedName("backgroundColor") val backgroundColor: String?,
    @SerializedName("text") val text: String?,
    @SerializedName("textColor") val textColor: String?
)

data class CaptionResponse(
    @SerializedName("cta") val cta: CtaResponse?,
    @SerializedName("description") val description: String?,
    @SerializedName("heading") val heading: HeadingResponse?,
    @SerializedName("isInverted") val isInverted: Boolean?,
    @SerializedName("position") val position: PositionResponse?
)