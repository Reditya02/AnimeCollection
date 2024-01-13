package com.example.animecollection.core

sealed class UIState<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : UIState<T>(data)
    class Loading : UIState<Nothing>()
    class Error(message: String?) : UIState<Nothing>(message = message)
}