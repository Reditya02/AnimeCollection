package com.example.animecollection.domain.usecase

import com.example.animecollection.domain.repository.IAnimeDatabaseRepository
import javax.inject.Inject

class GetGenreUseCase @Inject constructor(
    private val repository: IAnimeDatabaseRepository
) {
    operator fun invoke(id: String) =
        repository.getGenre(id)
}