package com.example.animecollection.ui.detail

import com.example.animecollection.domain.model.AnimeDetail

data class DetailState(
    val anime: AnimeDetail = AnimeDetail(
        rating = "",
        posterImage = "",
        coverImage = "",
        titleEn = "",
        titleJp = "",
        synopsis = "",
        genre = listOf(),
        characters = listOf()
    ),
    val isLoading: Boolean = false,
    val message: String = ""
)
