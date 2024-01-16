package com.example.animecollection.ui.detail.model

import com.example.animecollection.domain.model.Anime

data class DetailState(
    val anime: Anime = Anime(
        id = "",
        rating = "",
        posterImage = "",
        coverImage = "",
        titleEn = "",
        titleJp = "",
        synopsis = "",
        genre = listOf()
    ),
    val isLoading: Boolean = true,
    val message: String = ""
)
