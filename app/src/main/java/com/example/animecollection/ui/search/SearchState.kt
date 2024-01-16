package com.example.animecollection.ui.search

import com.example.animecollection.domain.model.Anime

data class SearchState(
    val query: String = "",
    val result: List<Anime> = listOf(),
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val errorMessage: String = ""
)
