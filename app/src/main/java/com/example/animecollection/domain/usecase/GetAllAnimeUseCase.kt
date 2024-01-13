package com.example.animecollection.domain.usecase

import com.example.animecollection.core.UIState
import com.example.animecollection.domain.model.Anime
import com.example.animecollection.domain.repository.IAnimeDatabaseRepository
import kotlinx.coroutines.flow.Flow

class GetAllAnimeUseCase(
    private val repository: IAnimeDatabaseRepository
) {
    operator fun invoke(): Flow<UIState<List<Anime>>> =
        repository.getAllAnime()
}