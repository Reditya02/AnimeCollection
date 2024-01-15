package com.example.animecollection.ui.detail.model

import com.example.animecollection.domain.model.AnimeDetail

data class DetailState(
    val anime: AnimeDetail = AnimeDetail(
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
