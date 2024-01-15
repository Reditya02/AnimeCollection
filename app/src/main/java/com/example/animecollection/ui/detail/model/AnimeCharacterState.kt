package com.example.animecollection.ui.detail.model

import com.example.animecollection.domain.model.AnimeCharacter

data class AnimeCharacterState(
    val character: List<AnimeCharacter> = listOf(),
    val isLoading: Boolean = true,
    val message: String = ""
)