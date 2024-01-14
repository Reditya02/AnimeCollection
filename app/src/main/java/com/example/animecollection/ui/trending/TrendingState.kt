package com.example.animecollection.ui.trending

import com.example.animecollection.domain.model.Anime

data class TrendingState(
    val listAnime: List<Anime> = listOf(),
    val isLoading: Boolean = false
)
