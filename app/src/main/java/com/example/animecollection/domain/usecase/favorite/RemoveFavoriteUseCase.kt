package com.example.animecollection.domain.usecase.favorite

import com.example.animecollection.domain.repository.IUserRepository
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(
    private val repository: IUserRepository
) {
    operator fun invoke(id: String) =
        repository.removeFavorite(id)
}