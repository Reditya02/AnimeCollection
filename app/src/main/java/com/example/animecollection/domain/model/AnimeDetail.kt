package com.example.animecollection.domain.model

data class AnimeDetail(
    val posterImage: String,
    val coverImage: String,
    val titleEn: String,
    val titleJp: String,
    val rating: String,
    val synopsis: String,
    val genre: List<String>,
    val characters: List<AnimeCharacter>
)
