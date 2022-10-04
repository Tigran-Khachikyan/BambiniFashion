package com.example.bambinifashion.data

interface Mapper<MODEL> {
    fun toDomain(): MODEL
}
