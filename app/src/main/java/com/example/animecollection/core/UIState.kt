package com.example.animecollection.core

sealed class UIState<out T>(val data: T? = null, val message: String? = null) {
    class Success<out T>(data: T) : UIState<T>(data)
    class Loading : UIState<Nothing>()
    class Error(message: String?) : UIState<Nothing>(message = message)
}