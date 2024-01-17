package com.example.animecollection.domain.usecase

import com.example.animecollection.domain.model.Anime
import com.example.animecollection.domain.repository.IUserRepository
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(
    private val repository: IUserRepository
) {
    operator fun invoke(anime: Anime) =
        repository.addFavorite(anime)
}