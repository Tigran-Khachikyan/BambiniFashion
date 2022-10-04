package com.example.bambinifashion.domain

sealed class Request<out T> {
    data class Success<T>(val data: T) : Request<T>()
    data class Error(val code: Int?) : Request<Nothing>()
}
