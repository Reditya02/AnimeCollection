package com.example.animecollection.domain.usecase

import com.example.animecollection.core.UIState
import com.example.animecollection.domain.model.AnimeDetail
import com.example.animecollection.domain.repository.IAnimeDatabaseRepository
import kotlinx.coroutines.flow.Flow

class GetDetailAnimeUseCase(
    private val repository: IAnimeDatabaseRepository
) {
    operator fun invoke(id: String): Flow<UIState<AnimeDetail>> =
        repository.getDetailAnime(id)
}