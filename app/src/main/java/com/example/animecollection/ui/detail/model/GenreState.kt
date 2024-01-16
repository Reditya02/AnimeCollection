package com.example.animecollection.ui.detail.model

data class GenreState(
    val genre: List<String> = listOf(),
    val isLoading: Boolean = true,
    val message: String = ""
)
