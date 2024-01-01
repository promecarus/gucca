package com.promecarus.core.data.remote

sealed class ApiResponse<out T> private constructor() {
    data object Loading : ApiResponse<Nothing>()
    data class Success<out T : Any>(val data: T) : ApiResponse<T>()
    data class Error(val message: String) : ApiResponse<Nothing>()
}
