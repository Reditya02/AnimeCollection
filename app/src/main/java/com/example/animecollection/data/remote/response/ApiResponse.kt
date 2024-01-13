package com.example.animecollection.data.remote.response

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val message: String) : ApiResponse<Nothing>()
    object Empty : ApiResponse<Nothing>()
}
