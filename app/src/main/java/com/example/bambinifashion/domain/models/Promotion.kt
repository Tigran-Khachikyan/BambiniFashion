package com.example.bambinifashion.domain.models

data class Promotion(
    val content: String,
    val duration: Long,
    val periodicity: Int,
    val backgroundColor: String,
    val textColor: String
) {
    companion object {
        const val default_duration = 3000L
        const val default_periodicity = 1
        const val default_text_color = "#f7f5f7"
        const val default_background_color = "#ff1100"
    }
}