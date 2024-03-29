package com.example.animecollection.domain.repository

import com.example.animecollection.core.UIState
import com.example.animecollection.domain.model.AnimeCharacter
import com.example.animecollection.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface IAnimeDatabaseRepository {
    fun getAllAnime(): Flow<UIState<List<Anime>>>
    fun getAnimeCharacter(id: String): Flow<UIState<List<AnimeCharacter>>>
    fun searchAnime(query: String): Flow<UIState<List<Anime>>>
    fun getGenre(id: String): Flow<UIState<List<String>>>
}